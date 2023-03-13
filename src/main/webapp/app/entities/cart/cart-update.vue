<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="blogApp.cart.home.createOrEditLabel" data-cy="CartCreateUpdateHeading" v-text="$t('blogApp.cart.home.createOrEditLabel')">
          Create or edit a Cart
        </h2>
        <div>
          <div class="form-group" v-if="cart.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="cart.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.cart.amount')" for="cart-amount">Amount</label>
            <input
              type="number"
              class="form-control"
              name="amount"
              id="cart-amount"
              data-cy="amount"
              :class="{ valid: !$v.cart.amount.$invalid, invalid: $v.cart.amount.$invalid }"
              v-model.number="$v.cart.amount.$model"
              required
            />
            <div v-if="$v.cart.amount.$anyDirty && $v.cart.amount.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.amount.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.amount.min" v-text="$t('entity.validation.min', { min: 0 })">
                This field should be at least 0.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.amount.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.cart.deadline')" for="cart-deadline">Deadline</label>
            <input
              type="number"
              class="form-control"
              name="deadline"
              id="cart-deadline"
              data-cy="deadline"
              :class="{ valid: !$v.cart.deadline.$invalid, invalid: $v.cart.deadline.$invalid }"
              v-model.number="$v.cart.deadline.$model"
              required
            />
            <div v-if="$v.cart.deadline.$anyDirty && $v.cart.deadline.$invalid">
              <small class="form-text text-danger" v-if="!$v.cart.deadline.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.cart.deadline.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.cart.command')" for="cart-command">Command</label>
            <select class="form-control" id="cart-command" data-cy="command" name="command" v-model="cart.command">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cart.command && commandOption.id === cart.command.id ? cart.command : commandOption"
                v-for="commandOption in commands"
                :key="commandOption.id"
              >
                {{ commandOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.cart.client')" for="cart-client">Client</label>
            <select class="form-control" id="cart-client" data-cy="client" name="client" v-model="cart.client">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cart.client && clientOption.id === cart.client.id ? cart.client : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('blogApp.cart.shop')" for="cart-shop">Shop</label>
            <select class="form-control" id="cart-shop" data-cy="shop" name="shop" v-model="cart.shop">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="cart.shop && shopOption.id === cart.shop.id ? cart.shop : shopOption"
                v-for="shopOption in shops"
                :key="shopOption.id"
              >
                {{ shopOption.id }}
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
            :disabled="$v.cart.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./cart-update.component.ts"></script>
