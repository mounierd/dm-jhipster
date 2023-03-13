/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import CommandDetailComponent from '@/entities/command/command-details.vue';
import CommandClass from '@/entities/command/command-details.component';
import CommandService from '@/entities/command/command.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('Command Management Detail Component', () => {
    let wrapper: Wrapper<CommandClass>;
    let comp: CommandClass;
    let commandServiceStub: SinonStubbedInstance<CommandService>;

    beforeEach(() => {
      commandServiceStub = sinon.createStubInstance<CommandService>(CommandService);

      wrapper = shallowMount<CommandClass>(CommandDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { commandService: () => commandServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundCommand = { id: 123 };
        commandServiceStub.find.resolves(foundCommand);

        // WHEN
        comp.retrieveCommand(123);
        await comp.$nextTick();

        // THEN
        expect(comp.command).toBe(foundCommand);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCommand = { id: 123 };
        commandServiceStub.find.resolves(foundCommand);

        // WHEN
        comp.beforeRouteEnter({ params: { commandId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.command).toBe(foundCommand);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
