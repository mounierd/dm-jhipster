import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IShop } from '@/shared/model/shop.model';

import ShopService from './shop.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Shop extends Vue {
  @Inject('shopService') private shopService: () => ShopService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public shops: IShop[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllShops();
  }

  public clear(): void {
    this.retrieveAllShops();
  }

  public retrieveAllShops(): void {
    this.isFetching = true;
    this.shopService()
      .retrieve()
      .then(
        res => {
          this.shops = res.data;
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

  public prepareRemove(instance: IShop): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeShop(): void {
    this.shopService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('blogApp.shop.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllShops();
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
