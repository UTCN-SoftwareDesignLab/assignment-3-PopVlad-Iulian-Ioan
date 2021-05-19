<template>
  <v-dialog
      transition="dialog-bottom-transition"
      max-width="600"
      :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create Consultation" : "Edit Consultation" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="consultation.doctorId" label="doctorId" />
          <v-text-field v-model="consultation.patientId" label="patientId" />
          <v-text-field v-model="consultation.startDate" label="startDate" />
          <v-text-field v-model="consultation.endDate" label="endDate" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
          <v-btn @click="erase">
            delete
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "ConsultationDialog",
  props: {
    consultation: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.consultations
            .create({
              doctorId: this.consultation.doctorId,
              patientId: this.consultation.patientId,
              startDate: this.consultation.startDate,
              endDate: this.consultation.endDate,
            })
            .then(() => this.$emit("refresh"));
      } else {
        api.consultations
            .edit({
              id: this.consultation.id,
              doctorId: this.consultation.doctorId,
              patientId: this.consultation.patientId,
              startDate: this.consultation.startDate,
              endDate: this.consultation.endDate,
            })
            .then(() => this.$emit("refresh"));
      }
    },
    erase(){
      api.consultations.delete(this.consultation.id)
          .then(() => this.$emit("refresh"));
    }
  },
  computed: {
    isNew: function () {
      return !this.consultation.id;
    },
  },
};
</script>

<style scoped></style>
