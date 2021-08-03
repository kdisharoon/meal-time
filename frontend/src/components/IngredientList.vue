<template>
  
</template>

<script>
import boardsService from '../services/BoardService';
import BoardColumn from '@/components/BoardColumn';

export default {
  name: 'cards-list',
  components: {
    BoardColumn
  },
  data() {
    return {
      title: '',
      cards: [],
      isLoading: true
    };
  },
  created() {
    boardsService.getCards(this.$route.params.id).then(response => {
      this.title = response.data.title;
      this.cards = response.data.cards;
      this.isLoading = false;
    });
  },
}
</script>

<style>

</style>