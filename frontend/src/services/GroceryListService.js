import axios from 'axios';


export default {

    getUserGroceryList(userID) {
        return axios.get(`/users/${userID}/grocerylist`);
    }

    // needs to add an ingredient to the grocery list, delete from the grocery list.

    // maybe incorporate an API for prices?

    // quantity of each item on the list? Is this a new database field?

}