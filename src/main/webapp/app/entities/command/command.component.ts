import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ICommand } from '@/shared/model/command.model';

import CommandService from './command.service';
import AlertService from '@/shared/alert/alert.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Command extends Vue {
  @Inject('commandService') private commandService: () => CommandService;
  @Inject('alertService') private alertService: () => AlertService;

  private removeId: number = null;

  public commands: ICommand[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllCommands();
  }

  public clear(): void {
    this.retrieveAllCommands();
  }

  public retrieveAllCommands(): void {
    this.isFetching = true;
    this.commandService()
      .retrieve()
      .then(
        res => {
          this.commands = res.data;
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

  public prepareRemove(instance: ICommand): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeCommand(): void {
    this.commandService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('blogApp.command.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllCommands();
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
