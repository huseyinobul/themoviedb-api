@smoke
Feature: Rate a movie

  @positive_rating_movie
  Scenario: Add rating for the movie by movie's id
    Given check The API with valid "api_key" and the API should be available
    When user send request to endpoint "/movie/{movie_id}/rating" with following valid query params
      | id            | 150                            |
      | api_key       | api_key                        |
      | rating_number | 8.5                            |
      | session_id    | session_id                     |
      | Content-Type  | application/json;charset=utf-8 |
    Then the response status code should be 201 and "status_message" should be "The item/record was updated successfully."


