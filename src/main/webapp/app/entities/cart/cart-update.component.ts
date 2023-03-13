import { Component, Vue, Inject } from 'vue-property-decorator';

import { decimal, required, minValue } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import CommandService from '@/entities/command/command.service';
import { ICommand } from '@/shared/model/command.model';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import ShopService from '@/entities/shop/shop.service';
import { IShop } from '@/shared/model/shop.model';

import { ICart, Cart } from '@/shared/model/cart.model';
import CartService from './cart.service';

const validations: any = {
  cart: {
    amount: {
      required,
      decimal,
      min: minValue(0),
    },
    deadline: {
      required,
      decimal,
    },
  },
};

@Component({
  validations,
})
export default class CartUpdate extends Vue {
  @Inject('cartService') private cartService: () => CartService;
  @Inject('alertService') private alertService: () => AlertService;

  public cart: ICart = new Cart();

  @Inject('commandService') private commandService: () => CommandService;

  public commands: ICommand[] = [];

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('shopService') private shopService: () => ShopService;

  public shops: IShop[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.cartId) {
        vm.retrieveCart(to.params.cartId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.cart.id) {
      this.cartService()
        .update(this.cart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.cart.updated', { param: param.id });
          return (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.cartService()
        .create(this.cart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.cart.created', { param: param.id });
          (this.$root as any).$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveCart(cartId): void {
    this.cartService()
      .find(cartId)
      .then(res => {
        this.cart = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.commandService()
      .retrieve()
      .then(res => {
        this.commands = res.data;
      });
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.shopService()
      .retrieve()
      .then(res => {
        this.shops = res.data;
      });
  }
}
