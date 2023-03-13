import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import CommandService from '@/entities/command/command.service';
import { ICommand } from '@/shared/model/command.model';

import CooperativeService from '@/entities/cooperative/cooperative.service';
import { ICooperative } from '@/shared/model/cooperative.model';

import { IDriver, Driver } from '@/shared/model/driver.model';
import DriverService from './driver.service';

const validations: any = {
  driver: {
    firstnameDriver: {
      required,
      maxLength: maxLength(30),
    },
    lastnameDriver: {
      required,
      maxLength: maxLength(30),
    },
    phoneCountryCodeDriver: {},
    phoneDriver: {},
  },
};

@Component({
  validations,
})
export default class DriverUpdate extends Vue {
  @Inject('driverService') private driverService: () => DriverService;
  @Inject('alertService') private alertService: () => AlertService;

  public driver: IDriver = new Driver();

  @Inject('commandService') private commandService: () => CommandService;

  public commands: ICommand[] = [];

  @Inject('cooperativeService') private cooperativeService: () => CooperativeService;

  public cooperatives: ICooperative[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.driverId) {
        vm.retrieveDriver(to.params.driverId);
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
    if (this.driver.id) {
      this.driverService()
        .update(this.driver)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.driver.updated', { param: param.id });
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
      this.driverService()
        .create(this.driver)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('blogApp.driver.created', { param: param.id });
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

  public retrieveDriver(driverId): void {
    this.driverService()
      .find(driverId)
      .then(res => {
        this.driver = res;
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
    this.cooperativeService()
      .retrieve()
      .then(res => {
        this.cooperatives = res.data;
      });
  }
}
