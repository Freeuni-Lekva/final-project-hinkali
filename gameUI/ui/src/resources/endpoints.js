const BASE_URL = 'http://localhost:9090/'
export const ENDPOINT_PLAYER_INFO = BASE_URL + 'game/info'
export const ENDPOINT_GAME_STATE = BASE_URL + 'game/state'
export const ENDPOINT_PLAY_CARD = (id) => BASE_URL + `game/playCard?id=${id}`
export const ENDPOINT_PASS_ROUND = BASE_URL + 'game/passRound'
export const ENDPOINT_GAME_OVER = BASE_URL + `game_over`