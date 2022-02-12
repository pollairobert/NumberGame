import axios from 'axios';
import { SERVER_URL } from '../Services/serverUrl';
import { emptyGamer } from "../Services/emptyGamer"

const API_URL = SERVER_URL + '/api/game/';
class ApiService {
  regOrLog(gamerName) {
    const endpoint = 'regorlog';
    const requestData = emptyGamer;
    requestData.name = gamerName;
    return axios.post(API_URL + endpoint, requestData);
  }

  startGame(gamer) {
    const endpoint = "start";
    const requestData = {...gamer};
    return axios.post(API_URL + endpoint, requestData);
  }
  tippNumber(gamer) {
    const endpoint = "tipp";
    const requestData = {...gamer};
    return axios.post(API_URL + endpoint, requestData);
  }
  getGameStats(id) {
    const endpoint =id ? "list/"+id : "list";
    return axios.get(API_URL + endpoint);
  }
}
export default new ApiService();