<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="cart">
        <h2 class="jh-entity-heading" data-cy="cartDetailsHeading">
          <span v-text="$t('blogApp.cart.detail.title')">Cart</span> {{ cart.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('blogApp.cart.amount')">Amount</span>
          </dt>
          <dd>
            <span>{{ cart.amount }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.cart.deadline')">Deadline</span>
          </dt>
          <dd>
            <span>{{ cart.deadline }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.cart.command')">Command</span>
          </dt>
          <dd>
            <div v-if="cart.command">
              <router-link :to="{ name: 'CommandView', params: { commandId: cart.command.id } }">{{ cart.command.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('blogApp.cart.client')">Client</span>
          </dt>
          <dd>
            <div v-if="cart.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: cart.client.id } }">{{ cart.client.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('blogApp.cart.shop')">Shop</span>
          </dt>
          <dd>
            <div v-if="cart.shop">
              <router-link :to="{ name: 'ShopView', params: { shopId: cart.shop.id } }">{{ cart.shop.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="cart.id" :to="{ name: 'CartEdit', params: { cartId: cart.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./cart-details.component.ts"></script>
