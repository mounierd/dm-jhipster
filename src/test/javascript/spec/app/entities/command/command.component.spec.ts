/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CommandComponent from '@/entities/command/command.vue';
import CommandClass from '@/entities/command/command.component';
import CommandService from '@/entities/command/command.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('Command Management Component', () => {
    let wrapper: Wrapper<CommandClass>;
    let comp: CommandClass;
    let commandServiceStub: SinonStubbedInstance<CommandService>;

    beforeEach(() => {
      commandServiceStub = sinon.createStubInstance<CommandService>(CommandService);
      commandServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<CommandClass>(CommandComponent, {
        store,
        i18n,
        localVue,
        stubs: { bModal: bModalStub as any },
        provide: {
          commandService: () => commandServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      commandServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllCommands();
      await comp.$nextTick();

      // THEN
      expect(commandServiceStub.retrieve.called).toBeTruthy();
      expect(comp.commands[0]).toEqual(expect.objectContaining({ id: 123 }));
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      commandServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(commandServiceStub.retrieve.callCount).toEqual(1);

      comp.removeCommand();
      await comp.$nextTick();

      // THEN
      expect(commandServiceStub.delete.called).toBeTruthy();
      expect(commandServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
