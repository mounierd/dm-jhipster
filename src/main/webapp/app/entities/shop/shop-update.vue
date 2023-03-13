<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="blogApp.shop.home.createOrEditLabel" data-cy="ShopCreateUpdateHeading" v-text="$t('blogApp.shop.home.createOrEditLabel')">
          Create or edit a Shop
        </h2>
        <div>
          <div class="form-group" v-if="shop.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="shop.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.shop.addressS')" for="shop-addressS">Address S</label>
            <input
              type="text"
              class="form-control"
              name="addressS"
              id="shop-addressS"
              data-cy="addressS"
              :class="{ valid: !$v.shop.addressS.$invalid, invalid: $v.shop.addressS.$invalid }"
              v-model="$v.shop.addressS.$model"
              required
            />
            <div v-if="$v.shop.addressS.$anyDirty && $v.shop.addressS.$invalid">
              <small class="form-text text-danger" v-if="!$v.shop.addressS.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.shop.menu')" for="shop-menu">Menu</label>
            <input
              type="text"
              class="form-control"
              name="menu"
              id="shop-menu"
              data-cy="menu"
              :class="{ valid: !$v.shop.menu.$invalid, invalid: $v.shop.menu.$invalid }"
              v-model="$v.shop.menu.$model"
            />
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
            :disabled="$v.shop.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./shop-update.component.ts"></script>
