<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="command">
        <h2 class="jh-entity-heading" data-cy="commandDetailsHeading">
          <span v-text="$t('blogApp.command.detail.title')">Command</span> {{ command.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('blogApp.command.addressClient')">Address Client</span>
          </dt>
          <dd>
            <span>{{ command.addressClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.command.dateClient')">Date Client</span>
          </dt>
          <dd>
            <span>{{ command.dateClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.command.client')">Client</span>
          </dt>
          <dd>
            <div v-if="command.client">
              <router-link :to="{ name: 'ClientView', params: { clientId: command.client.id } }">{{ command.client.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('blogApp.command.driver')">Driver</span>
          </dt>
          <dd>
            <div v-if="command.driver">
              <router-link :to="{ name: 'DriverView', params: { driverId: command.driver.id } }">{{ command.driver.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="command.id" :to="{ name: 'CommandEdit', params: { commandId: command.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./command-details.component.ts"></script>
