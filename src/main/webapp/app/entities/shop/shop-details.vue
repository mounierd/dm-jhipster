<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="shop">
        <h2 class="jh-entity-heading" data-cy="shopDetailsHeading">
          <span v-text="$t('blogApp.shop.detail.title')">Shop</span> {{ shop.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('blogApp.shop.addressS')">Address S</span>
          </dt>
          <dd>
            <span>{{ shop.addressS }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.shop.menu')">Menu</span>
          </dt>
          <dd>
            <span>{{ shop.menu }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="shop.id" :to="{ name: 'ShopEdit', params: { shopId: shop.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./shop-details.component.ts"></script>
