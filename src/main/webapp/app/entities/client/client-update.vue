<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="blogApp.client.home.createOrEditLabel"
          data-cy="ClientCreateUpdateHeading"
          v-text="$t('blogApp.client.home.createOrEditLabel')"
        >
          Create or edit a Client
        </h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.idClient')" for="client-idClient">Id Client</label>
            <input
              type="text"
              class="form-control"
              name="idClient"
              id="client-idClient"
              data-cy="idClient"
              :class="{ valid: !$v.client.idClient.$invalid, invalid: $v.client.idClient.$invalid }"
              v-model="$v.client.idClient.$model"
              required
            />
            <div v-if="$v.client.idClient.$anyDirty && $v.client.idClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.idClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.firstnameClient')" for="client-firstnameClient"
              >Firstname Client</label
            >
            <input
              type="text"
              class="form-control"
              name="firstnameClient"
              id="client-firstnameClient"
              data-cy="firstnameClient"
              :class="{ valid: !$v.client.firstnameClient.$invalid, invalid: $v.client.firstnameClient.$invalid }"
              v-model="$v.client.firstnameClient.$model"
              required
            />
            <div v-if="$v.client.firstnameClient.$anyDirty && $v.client.firstnameClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.firstnameClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.firstnameClient.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.firstnameClient.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Firstname Client' })"
              >
                This field should follow pattern for "Firstname Client".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.lastnameClient')" for="client-lastnameClient"
              >Lastname Client</label
            >
            <input
              type="text"
              class="form-control"
              name="lastnameClient"
              id="client-lastnameClient"
              data-cy="lastnameClient"
              :class="{ valid: !$v.client.lastnameClient.$invalid, invalid: $v.client.lastnameClient.$invalid }"
              v-model="$v.client.lastnameClient.$model"
              required
            />
            <div v-if="$v.client.lastnameClient.$anyDirty && $v.client.lastnameClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.lastnameClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.lastnameClient.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.lastnameClient.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Lastname Client' })"
              >
                This field should follow pattern for "Lastname Client".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.emailClient')" for="client-emailClient">Email Client</label>
            <input
              type="text"
              class="form-control"
              name="emailClient"
              id="client-emailClient"
              data-cy="emailClient"
              :class="{ valid: !$v.client.emailClient.$invalid, invalid: $v.client.emailClient.$invalid }"
              v-model="$v.client.emailClient.$model"
              required
            />
            <div v-if="$v.client.emailClient.$anyDirty && $v.client.emailClient.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.emailClient.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.emailClient.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.emailClient.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Email Client' })"
              >
                This field should follow pattern for "Email Client".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.phoneCountryCodeClient')" for="client-phoneCountryCodeClient"
              >Phone Country Code Client</label
            >
            <input
              type="number"
              class="form-control"
              name="phoneCountryCodeClient"
              id="client-phoneCountryCodeClient"
              data-cy="phoneCountryCodeClient"
              :class="{ valid: !$v.client.phoneCountryCodeClient.$invalid, invalid: $v.client.phoneCountryCodeClient.$invalid }"
              v-model.number="$v.client.phoneCountryCodeClient.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.phoneClient')" for="client-phoneClient">Phone Client</label>
            <input
              type="number"
              class="form-control"
              name="phoneClient"
              id="client-phoneClient"
              data-cy="phoneClient"
              :class="{ valid: !$v.client.phoneClient.$invalid, invalid: $v.client.phoneClient.$invalid }"
              v-model.number="$v.client.phoneClient.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.client.addressC')" for="client-addressC">Address C</label>
            <input
              type="text"
              class="form-control"
              name="addressC"
              id="client-addressC"
              data-cy="addressC"
              :class="{ valid: !$v.client.addressC.$invalid, invalid: $v.client.addressC.$invalid }"
              v-model="$v.client.addressC.$model"
              required
            />
            <div v-if="$v.client.addressC.$anyDirty && $v.client.addressC.$invalid">
              <small class="form-text text-danger" v-if="!$v.client.addressC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.client.addressC.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
            </div>
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
            :disabled="$v.client.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-update.component.ts"></script>
