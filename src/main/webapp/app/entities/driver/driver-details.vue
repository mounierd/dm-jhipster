<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="driver">
        <h2 class="jh-entity-heading" data-cy="driverDetailsHeading">
          <span v-text="$t('blogApp.driver.detail.title')">Driver</span> {{ driver.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('blogApp.driver.firstnameDriver')">Firstname Driver</span>
          </dt>
          <dd>
            <span>{{ driver.firstnameDriver }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.driver.lastnameDriver')">Lastname Driver</span>
          </dt>
          <dd>
            <span>{{ driver.lastnameDriver }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.driver.phoneCountryCodeDriver')">Phone Country Code Driver</span>
          </dt>
          <dd>
            <span>{{ driver.phoneCountryCodeDriver }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.driver.phoneDriver')">Phone Driver</span>
          </dt>
          <dd>
            <span>{{ driver.phoneDriver }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.driver.cooperative')">Cooperative</span>
          </dt>
          <dd>
            <div v-if="driver.cooperative">
              <router-link :to="{ name: 'CooperativeView', params: { cooperativeId: driver.cooperative.id } }">{{
                driver.cooperative.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="driver.id" :to="{ name: 'DriverEdit', params: { driverId: driver.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./driver-details.component.ts"></script>
