import { Component, Vue, Inject } from 'vue-property-decorator';

import { IShop } from '@/shared/model/shop.model';
import ShopService from './shop.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ShopDetails extends Vue {
  @Inject('shopService') private shopService: () => ShopService;
  @Inject('alertService') private alertService: () => AlertService;

  public shop: IShop = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.shopId) {
        vm.retrieveShop(to.params.shopId);
      }
    });
  }

  public retrieveShop(shopId) {
    this.shopService()
      .find(shopId)
      .then(res => {
        this.shop = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
