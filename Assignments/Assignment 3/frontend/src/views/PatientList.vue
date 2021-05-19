<template>
  <v-card>
    <v-card-title>
      Patient
      <v-spacer></v-spacer>
      <v-spacer></v-spacer>
      <v-btn @click="addPatient">Add Patient</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="patients"
        :search="search"
        @click:row="editPatient"
    ></v-data-table>
    <PatientDialog
        :opened="createDialogVisible"
        :patient="selectedItem"
        @refresh="refreshList"
    ></PatientDialog>
  </v-card>
</template>

<script>
import api from "../api";
import PatientDialog from "../components/PatientDialog";

export default {
  name: "PatientList",
  components: {PatientDialog },
  data() {
    return {
      patients: [],
      search: "",
      headers: [
        {
          text: "name",
          align: "start",
          sortable: false,
          value: "name",
        },
        { text: "icn", value: "icn" },
        { text: "cnp", value: " cnp" },
        { text: "dateOfBirth", value: "dateOfBirth" },
        { text: "address", value: "address" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editPatient(patient) {
      this.selectedItem = patient;
      this.dialogVisible = true;
    },
    addPatient() {
      this.createDialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.consultations = await api.consultations.allPatients();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
