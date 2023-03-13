<template>
  <div>
    <b-dropdown-item to="/driver">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.driver')">Driver</span>
    </b-dropdown-item>
    <b-dropdown-item to="/car">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.car')">Car</span>
    </b-dropdown-item>
    <b-dropdown-item to="/client">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.client')">Client</span>
    </b-dropdown-item>
    <b-dropdown-item to="/cooperative">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.cooperative')">Cooperative</span>
    </b-dropdown-item>
    <b-dropdown-item to="/shop">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.shop')">Shop</span>
    </b-dropdown-item>
    <b-dropdown-item to="/cart">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.cart')">Cart</span>
    </b-dropdown-item>
    <b-dropdown-item to="/command">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.command')">Command</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
