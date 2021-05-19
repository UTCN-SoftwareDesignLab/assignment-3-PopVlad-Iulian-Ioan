<template>
  <v-card>
    <v-card-title>
      Consultations
      <v-spacer></v-spacer>

      <v-btn @click="addConsultation">Add Consultation</v-btn>
    </v-card-title>
    <v-data-table
        :headers="headers"
        :items="consultations"
        :search="search"
        @click:row="editConsultation"
    ></v-data-table>
    <ConsultationDialog
        :opened="createDialogVisible"
        :consultation="selectedItem"
        @refresh="refreshList"
    ></ConsultationDialog>
  </v-card>
</template>

<script>
import api from "../api";
import ConsultationDialog from "../components/ConsultationDialog";

export default {
  name: "ConsultationList",
  components: {ConsultationDialog },
  data() {
    return {
      consultations: [],
      search: "",
      headers: [
        {
          text: "doctorId",
          align: "start",
          sortable: false,
          value: "doctorId",
        },
        { text: "patientId", value: "patientId" },
        { text: "startDate", value: "startDate" },
        { text: "startEnd", value: "startEnd" },
      ],
      dialogVisible: false,
      createDialogVisible: false,
      selectedItem: {},
    };
  },
  methods: {
    editConsultation(consultation) {
      this.selectedItem = consultation;
      this.dialogVisible = true;
    },
    addConsultation() {
      this.createDialogVisible = true;
    },
    async refreshList() {
      this.dialogVisible = false;
      this.createDialogVisible = false;
      this.selectedItem = {};
      this.consultations = await api.consultations.allConsultations();
    },
  },
  created() {
    this.refreshList();
  },
};
</script>

<style scoped></style>
