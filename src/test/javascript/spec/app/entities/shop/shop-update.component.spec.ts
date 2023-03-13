/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ShopUpdateComponent from '@/entities/shop/shop-update.vue';
import ShopClass from '@/entities/shop/shop-update.component';
import ShopService from '@/entities/shop/shop.service';

import CartService from '@/entities/cart/cart.service';
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
  describe('Shop Management Update Component', () => {
    let wrapper: Wrapper<ShopClass>;
    let comp: ShopClass;
    let shopServiceStub: SinonStubbedInstance<ShopService>;

    beforeEach(() => {
      shopServiceStub = sinon.createStubInstance<ShopService>(ShopService);

      wrapper = shallowMount<ShopClass>(ShopUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          shopService: () => shopServiceStub,
          alertService: () => new AlertService(),

          cartService: () =>
            sinon.createStubInstance<CartService>(CartService, {
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
        comp.shop = entity;
        shopServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(shopServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.shop = entity;
        shopServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(shopServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundShop = { id: 123 };
        shopServiceStub.find.resolves(foundShop);
        shopServiceStub.retrieve.resolves([foundShop]);

        // WHEN
        comp.beforeRouteEnter({ params: { shopId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.shop).toBe(foundShop);
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
