# MyGames

#Design consideration steps
1.Framework - Google recommended MVVM
2.Libraries
    Network- Retrofit with GSON for response parsing
    Concurrency - Coroutine
    Image loading- Google recommended Coil for kotlin
    Dependency injection - Hilt
3. Considered packaging in app wrt feature/screen
4. UI- Constraint layout whenever possible
5. Recommended MIN SDk and latest Target SDK versions are used.
6. Mock-webserver square library used for testing network calls

# Things could have been improved or added:

# UX can be improved handling to failure cases while fetching data for list,
  eg. handling empty response from server or some other exception.
# Caching at repository level could have been added.
# We can think of using PAGING library from jetpack when we expect long list in response.
# To find out memory leak we can use leak canary library
# SONAR CUBE for code quality could have been used.
# Unit testing, with new latest coroutine test library 1.6.0 tests are failing,
  this needs little investigation
# UI tests can be added. I have very little knowledge about UI testing

