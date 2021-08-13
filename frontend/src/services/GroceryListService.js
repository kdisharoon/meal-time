import axios from 'axios';


export default {

    getUserGroceryList(userID) {
        return axios.get(`/users/${userID}/grocerylist`);
    }

}