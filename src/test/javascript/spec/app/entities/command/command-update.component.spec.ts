/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import CommandUpdateComponent from '@/entities/command/command-update.vue';
import CommandClass from '@/entities/command/command-update.component';
import CommandService from '@/entities/command/command.service';

import ClientService from '@/entities/client/client.service';

import CartService from '@/entities/cart/cart.service';

import DriverService from '@/entities/driver/driver.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('Command Management Update Component', () => {
    let wrapper: Wrapper<CommandClass>;
    let comp: CommandClass;
    let commandServiceStub: SinonStubbedInstance<CommandService>;

    beforeEach(() => {
      commandServiceStub = sinon.createStubInstance<CommandService>(CommandService);

      wrapper = shallowMount<CommandClass>(CommandUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          commandService: () => commandServiceStub,
          alertService: () => new AlertService(),

          clientService: () =>
            sinon.createStubInstance<ClientService>(ClientService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          cartService: () =>
            sinon.createStubInstance<CartService>(CartService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          driverService: () =>
            sinon.createStubInstance<DriverService>(DriverService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.command = entity;
        commandServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commandServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.command = entity;
        commandServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(commandServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundCommand = { id: 123 };
        commandServiceStub.find.resolves(foundCommand);
        commandServiceStub.retrieve.resolves([foundCommand]);

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
