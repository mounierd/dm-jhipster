/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ShopDetailComponent from '@/entities/shop/shop-details.vue';
import ShopClass from '@/entities/shop/shop-details.component';
import ShopService from '@/entities/shop/shop.service';
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
  describe('Shop Management Detail Component', () => {
    let wrapper: Wrapper<ShopClass>;
    let comp: ShopClass;
    let shopServiceStub: SinonStubbedInstance<ShopService>;

    beforeEach(() => {
      shopServiceStub = sinon.createStubInstance<ShopService>(ShopService);

      wrapper = shallowMount<ShopClass>(ShopDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { shopService: () => shopServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundShop = { id: 123 };
        shopServiceStub.find.resolves(foundShop);

        // WHEN
        comp.retrieveShop(123);
        await comp.$nextTick();

        // THEN
        expect(comp.shop).toBe(foundShop);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundShop = { id: 123 };
        shopServiceStub.find.resolves(foundShop);

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
