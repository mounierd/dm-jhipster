<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="blogApp.driver.home.createOrEditLabel"
          data-cy="DriverCreateUpdateHeading"
          v-text="$t('blogApp.driver.home.createOrEditLabel')"
        >
          Create or edit a Driver
        </h2>
        <div>
          <div class="form-group" v-if="driver.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="driver.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.driver.firstnameDriver')" for="driver-firstnameDriver"
              >Firstname Driver</label
            >
            <input
              type="text"
              class="form-control"
              name="firstnameDriver"
              id="driver-firstnameDriver"
              data-cy="firstnameDriver"
              :class="{ valid: !$v.driver.firstnameDriver.$invalid, invalid: $v.driver.firstnameDriver.$invalid }"
              v-model="$v.driver.firstnameDriver.$model"
              required
            />
            <div v-if="$v.driver.firstnameDriver.$anyDirty && $v.driver.firstnameDriver.$invalid">
              <small class="form-text text-danger" v-if="!$v.driver.firstnameDriver.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.driver.firstnameDriver.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.driver.firstnameDriver.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Firstname Driver' })"
              >
                This field should follow pattern for "Firstname Driver".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.driver.lastnameDriver')" for="driver-lastnameDriver"
              >Lastname Driver</label
            >
            <input
              type="text"
              class="form-control"
              name="lastnameDriver"
              id="driver-lastnameDriver"
              data-cy="lastnameDriver"
              :class="{ valid: !$v.driver.lastnameDriver.$invalid, invalid: $v.driver.lastnameDriver.$invalid }"
              v-model="$v.driver.lastnameDriver.$model"
              required
            />
            <div v-if="$v.driver.lastnameDriver.$anyDirty && $v.driver.lastnameDriver.$invalid">
              <small class="form-text text-danger" v-if="!$v.driver.lastnameDriver.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.driver.lastnameDriver.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 30 })"
              >
                This field cannot be longer than 30 characters.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.driver.lastnameDriver.pattern"
                v-text="$t('entity.validation.pattern', { pattern: 'Lastname Driver' })"
              >
                This field should follow pattern for "Lastname Driver".
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.driver.phoneCountryCodeDriver')" for="driver-phoneCountryCodeDriver"
              >Phone Country Code Driver</label
            >
            <input
              type="number"
              class="form-control"
              name="phoneCountryCodeDriver"
              id="driver-phoneCountryCodeDriver"
              data-cy="phoneCountryCodeDriver"
              :class="{ valid: !$v.driver.phoneCountryCodeDriver.$invalid, invalid: $v.driver.phoneCountryCodeDriver.$invalid }"
              v-model.number="$v.driver.phoneCountryCodeDriver.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.driver.phoneDriver')" for="driver-phoneDriver">Phone Driver</label>
            <input
              type="number"
              class="form-control"
              name="phoneDriver"
              id="driver-phoneDriver"
              data-cy="phoneDriver"
              :class="{ valid: !$v.driver.phoneDriver.$invalid, invalid: $v.driver.phoneDriver.$invalid }"
              v-model.number="$v.driver.phoneDriver.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.driver.cooperative')" for="driver-cooperative">Cooperative</label>
            <select class="form-control" id="driver-cooperative" data-cy="cooperative" name="cooperative" v-model="driver.cooperative">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="driver.cooperative && cooperativeOption.id === driver.cooperative.id ? driver.cooperative : cooperativeOption"
                v-for="cooperativeOption in cooperatives"
                :key="cooperativeOption.id"
              >
                {{ cooperativeOption.id }}
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
            :disabled="$v.driver.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./driver-update.component.ts"></script>
