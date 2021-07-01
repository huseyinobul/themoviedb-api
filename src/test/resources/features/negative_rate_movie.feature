@smoke
  Feature:Rate a movie

    @negative_rating_movie
    Scenario: Add rating for the movie by movie's id with invalid api_key
      Given check The API with valid "api_key" and the API should be available
      When user send request to endpoint "/movie/{movie_id}/rating" with invalid "session_id" or "api_key" and following valid query params
        | id            | 150                            |
        | rating_number | 8.5                            |
        | Content-Type  | application/json;charset=utf-8 |
      Then response status code should be 401 and "status_message" should be "Authentication failed: You do not have permissions to access the service."