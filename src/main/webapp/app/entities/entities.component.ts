import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import DriverService from './driver/driver.service';
import CarService from './car/car.service';
import ClientService from './client/client.service';
import CooperativeService from './cooperative/cooperative.service';
import ShopService from './shop/shop.service';
import CartService from './cart/cart.service';
import CommandService from './command/command.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('driverService') private driverService = () => new DriverService();
  @Provide('carService') private carService = () => new CarService();
  @Provide('clientService') private clientService = () => new ClientService();
  @Provide('cooperativeService') private cooperativeService = () => new CooperativeService();
  @Provide('shopService') private shopService = () => new ShopService();
  @Provide('cartService') private cartService = () => new CartService();
  @Provide('commandService') private commandService = () => new CommandService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
