import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ClientService from '@/entities/client/client.service';
import { IClient } from '@/shared/model/client.model';

import CartService from '@/entities/cart/cart.service';
import { ICart } from '@/shared/model/cart.model';

import DriverService from '@/entities/driver/driver.service';
import { IDriver } from '@/shared/model/driver.model';

import { ICommand, Command } from '@/shared/model/command.model';
import CommandService from './command.service';

const validations: any = {
  command: {
    addressClient: {
      required,
    },
    dateClient: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class CommandUpdate extends Vue {
  @Inject('commandService') private commandService: () => CommandService;
  @Inject('alertService') private alertService: () => AlertService;

  public command: ICommand = new Command();

  @Inject('clientService') private clientService: () => ClientService;

  public clients: IClient[] = [];

  @Inject('cartService') private cartService: () => CartService;

  public carts: ICart[] = [];

  @Inject('driverService') private driverService: () => DriverService;

  public drivers: IDriver[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commandId) {
        vm.retrieveCommand(to.params.commandId);
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
    if (this.command.id) {
      this.commandService()
        .update(this.command)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.command.updated', { param: param.id });
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
      this.commandService()
        .create(this.command)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.command.created', { param: param.id });
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

  public retrieveCommand(commandId): void {
    this.commandService()
      .find(commandId)
      .then(res => {
        this.command = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.clientService()
      .retrieve()
      .then(res => {
        this.clients = res.data;
      });
    this.cartService()
      .retrieve()
      .then(res => {
        this.carts = res.data;
      });
    this.driverService()
      .retrieve()
      .then(res => {
        this.drivers = res.data;
      });
  }
}
