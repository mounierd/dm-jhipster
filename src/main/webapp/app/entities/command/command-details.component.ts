import { Component, Vue, Inject } from 'vue-property-decorator';

import { ICommand } from '@/shared/model/command.model';
import CommandService from './command.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class CommandDetails extends Vue {
  @Inject('commandService') private commandService: () => CommandService;
  @Inject('alertService') private alertService: () => AlertService;

  public command: ICommand = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.commandId) {
        vm.retrieveCommand(to.params.commandId);
      }
    });
  }

  public retrieveCommand(commandId) {
    this.commandService()
      .find(commandId)
      .then(res => {
        this.command = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
