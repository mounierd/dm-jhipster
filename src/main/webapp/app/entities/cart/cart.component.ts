import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICart } from '@/shared/model/cart.model';

import CartService from './cart.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Cart extends Vue {
  @Inject('cartService') private cartService: () => CartService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public carts: ICart[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCarts();
  }

  public clear(): void {
    this.retrieveAllCarts();
  }

  public retrieveAllCarts(): void {
    this.isFetching = true;
    this.cartService()
      .retrieve()
      .then(
        res => {
          this.carts = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
          this.alertService().showHttpError(this, err.response);
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public prepareRemove(instance: ICart): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCart(): void {
    this.cartService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('blogApp.cart.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCarts();
        this.closeDialog();
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
