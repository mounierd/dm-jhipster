import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const Driver = () => import('@/entities/driver/driver.vue');
// prettier-ignore
const DriverUpdate = () => import('@/entities/driver/driver-update.vue');
// prettier-ignore
const DriverDetails = () => import('@/entities/driver/driver-details.vue');
// prettier-ignore
const Car = () => import('@/entities/car/car.vue');
// prettier-ignore
const CarUpdate = () => import('@/entities/car/car-update.vue');
// prettier-ignore
const CarDetails = () => import('@/entities/car/car-details.vue');
// prettier-ignore
const Client = () => import('@/entities/client/client.vue');
// prettier-ignore
const ClientUpdate = () => import('@/entities/client/client-update.vue');
// prettier-ignore
const ClientDetails = () => import('@/entities/client/client-details.vue');
// prettier-ignore
const Cooperative = () => import('@/entities/cooperative/cooperative.vue');
// prettier-ignore
const CooperativeUpdate = () => import('@/entities/cooperative/cooperative-update.vue');
// prettier-ignore
const CooperativeDetails = () => import('@/entities/cooperative/cooperative-details.vue');
// prettier-ignore
const Shop = () => import('@/entities/shop/shop.vue');
// prettier-ignore
const ShopUpdate = () => import('@/entities/shop/shop-update.vue');
// prettier-ignore
const ShopDetails = () => import('@/entities/shop/shop-details.vue');
// prettier-ignore
const Cart = () => import('@/entities/cart/cart.vue');
// prettier-ignore
const CartUpdate = () => import('@/entities/cart/cart-update.vue');
// prettier-ignore
const CartDetails = () => import('@/entities/cart/cart-details.vue');
// prettier-ignore
const Command = () => import('@/entities/command/command.vue');
// prettier-ignore
const CommandUpdate = () => import('@/entities/command/command-update.vue');
// prettier-ignore
const CommandDetails = () => import('@/entities/command/command-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'driver',
      name: 'Driver',
      component: Driver,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'driver/new',
      name: 'DriverCreate',
      component: DriverUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'driver/:driverId/edit',
      name: 'DriverEdit',
      component: DriverUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'driver/:driverId/view',
      name: 'DriverView',
      component: DriverDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'car',
      name: 'Car',
      component: Car,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'car/new',
      name: 'CarCreate',
      component: CarUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'car/:carId/edit',
      name: 'CarEdit',
      component: CarUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'car/:carId/view',
      name: 'CarView',
      component: CarDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client',
      name: 'Client',
      component: Client,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/new',
      name: 'ClientCreate',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/edit',
      name: 'ClientEdit',
      component: ClientUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'client/:clientId/view',
      name: 'ClientView',
      component: ClientDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative',
      name: 'Cooperative',
      component: Cooperative,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/new',
      name: 'CooperativeCreate',
      component: CooperativeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/:cooperativeId/edit',
      name: 'CooperativeEdit',
      component: CooperativeUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cooperative/:cooperativeId/view',
      name: 'CooperativeView',
      component: CooperativeDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop',
      name: 'Shop',
      component: Shop,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/new',
      name: 'ShopCreate',
      component: ShopUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/:shopId/edit',
      name: 'ShopEdit',
      component: ShopUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'shop/:shopId/view',
      name: 'ShopView',
      component: ShopDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart',
      name: 'Cart',
      component: Cart,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/new',
      name: 'CartCreate',
      component: CartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/:cartId/edit',
      name: 'CartEdit',
      component: CartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'cart/:cartId/view',
      name: 'CartView',
      component: CartDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'command',
      name: 'Command',
      component: Command,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'command/new',
      name: 'CommandCreate',
      component: CommandUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'command/:commandId/edit',
      name: 'CommandEdit',
      component: CommandUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'command/:commandId/view',
      name: 'CommandView',
      component: CommandDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
