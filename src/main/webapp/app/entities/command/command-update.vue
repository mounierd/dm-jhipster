<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="blogApp.command.home.createOrEditLabel"
          data-cy="CommandCreateUpdateHeading"
          v-text="$t('blogApp.command.home.createOrEditLabel')"
        >
          Create or edit a Command
        </h2>
        <div>
          <div class="form-group" v-if="command.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="command.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.command.addressClient')" for="command-addressClient"
              >Address Client</label
            >
            <input
              type="text"
              class="form-control"
              name="addressClient"
              id="command-addressClient"
              data-cy="addressClient"
              :class="{ valid: !$v.command.addressClient.$invalid, invalid: $v.command.addressClient.$invalid }"
              v-model="$v.command.addressClient.$model"
              required
            />
            <div v-if="$v.command.addressClient.$anyDirty && $v.command.addressClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.command.addressClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.command.dateClient')" for="command-dateClient">Date Client</label>
            <input
              type="text"
              class="form-control"
              name="dateClient"
              id="command-dateClient"
              data-cy="dateClient"
              :class="{ valid: !$v.command.dateClient.$invalid, invalid: $v.command.dateClient.$invalid }"
              v-model="$v.command.dateClient.$model"
              required
            />
            <div v-if="$v.command.dateClient.$anyDirty && $v.command.dateClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.command.dateClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.command.client')" for="command-client">Client</label>
            <select class="form-control" id="command-client" data-cy="client" name="client" v-model="command.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="command.client && clientOption.id === command.client.id ? command.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.command.driver')" for="command-driver">Driver</label>
            <select class="form-control" id="command-driver" data-cy="driver" name="driver" v-model="command.driver">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="command.driver && driverOption.id === command.driver.id ? command.driver : driverOption"
                v-for="driverOption in drivers"
                :key="driverOption.id"
              >
                {{ driverOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.command.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./command-update.component.ts"></script>
