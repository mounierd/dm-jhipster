<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="client">
        <h2 class="jh-entity-heading" data-cy="clientDetailsHeading">
          <span v-text="$t('blogApp.client.detail.title')">Client</span> {{ client.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('blogApp.client.idClient')">Id Client</span>
          </dt>
          <dd>
            <span>{{ client.idClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.firstnameClient')">Firstname Client</span>
          </dt>
          <dd>
            <span>{{ client.firstnameClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.lastnameClient')">Lastname Client</span>
          </dt>
          <dd>
            <span>{{ client.lastnameClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.emailClient')">Email Client</span>
          </dt>
          <dd>
            <span>{{ client.emailClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.phoneCountryCodeClient')">Phone Country Code Client</span>
          </dt>
          <dd>
            <span>{{ client.phoneCountryCodeClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.phoneClient')">Phone Client</span>
          </dt>
          <dd>
            <span>{{ client.phoneClient }}</span>
          </dd>
          <dt>
            <span v-text="$t('blogApp.client.addressC')">Address C</span>
          </dt>
          <dd>
            <span>{{ client.addressC }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="client.id" :to="{ name: 'ClientEdit', params: { clientId: client.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./client-details.component.ts"></script>
