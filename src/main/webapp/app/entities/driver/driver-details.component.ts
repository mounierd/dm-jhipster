import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDriver } from '@/shared/model/driver.model';
import DriverService from './driver.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DriverDetails extends Vue {
  @Inject('driverService') private driverService: () => DriverService;
  @Inject('alertService') private alertService: () => AlertService;

  public driver: IDriver = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.driverId) {
        vm.retrieveDriver(to.params.driverId);
      }
    });
  }

  public retrieveDriver(driverId) {
    this.driverService()
      .find(driverId)
      .then(res => {
        this.driver = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
