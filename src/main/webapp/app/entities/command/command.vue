<template>
  <div>
    <h2 id="page-heading" data-cy="CommandHeading">
      <span v-text="$t('blogApp.command.home.title')" id="command-heading">Commands</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('blogApp.command.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'CommandCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-command"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('blogApp.command.home.createLabel')"> Create a new Command </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && commands && commands.length === 0">
      <span v-text="$t('blogApp.command.home.notFound')">No commands found</span>
    </div>
    <div class="table-responsive" v-if="commands && commands.length > 0">
      <table class="table table-striped" aria-describedby="commands">
        <thead>
          <tr>
            <th scope="row"><span v-text="$t('global.field.id')">ID</span></th>
            <th scope="row"><span v-text="$t('blogApp.command.addressClient')">Address Client</span></th>
            <th scope="row"><span v-text="$t('blogApp.command.dateClient')">Date Client</span></th>
            <th scope="row"><span v-text="$t('blogApp.command.client')">Client</span></th>
            <th scope="row"><span v-text="$t('blogApp.command.driver')">Driver</span></th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="command in commands" :key="command.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'CommandView', params: { commandId: command.id } }">{{ command.id }}</router-link>
            </td>
            <td>{{ command.addressClient }}</td>
            <td>{{ command.dateClient }}</td>
            <td>
              <div v-if="command.client">
                <router-link :to="{ name: 'ClientView', params: { clientId: command.client.id } }">{{ command.client.id }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="command.driver">
                <router-link :to="{ name: 'DriverView', params: { driverId: command.driver.id } }">{{ command.driver.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'CommandView', params: { commandId: command.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'CommandEdit', params: { commandId: command.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(command)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="blogApp.command.delete.question" data-cy="commandDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-command-heading" v-text="$t('blogApp.command.delete.question', { id: removeId })">
          Are you sure you want to delete this Command?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-command"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeCommand()"
        >
          Delete
        </button>
      </div>
    </b-modal>
  </div>
</template>

<script lang="ts" src="./command.component.ts"></script>
