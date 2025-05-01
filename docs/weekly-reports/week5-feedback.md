# Intructor Feedback

## Progress evaluation :scroll:
- :white_check_mark: There is progress on Game Setup Phase
- :white_check_mark: The project magemenet board, GitHub Projects, is used.
- :white_check_mark: Spotbugs and Checkstyle are set up.


## Comments :speech_balloon:
1. Make sure to do git pull origin main often so the feature branch is up to date of the current state of the project.
2. Make sure to structure your project using the MVC pattern (see GUI Development session) --- it will make testing and development a lot easier, and make SpotBug errors easier to fix (as many bad coding practices are results of poor design)
3. Run SpotBugs and Checkstyle locally so you can fix the issue before pushing them into the repository (how: Gradle icon --> Tasks --> verification --> test, if the build script is all set up)
4. Consider this workflow: Team decides to do items and document them in the project management board --> assign the item to a person --> the person creates a feature branch --> once there is any commit made to the branch, open a DRAFT PR. This way, it is easier to see who is working on what.
5. I can see some attempts in PR title naming convention (with FEATURE or SETUP) --- good job! Make sure to be consistent across the team.
6. Code review comments:
1) Commit messages should be descriptive. (For instance, the commits in this PR https://github.com/nu-cs-sqe/course-project-20242510-team-01-20242503/pull/6 can be improved)
2) Deck.java line 8 --- the field should be of type List but not LinkedList.
3) Deck.java: to make the unit isolated from Linkedlist, should change
```
	Deck(SecureRandom rand) {
		this.deck = new LinkedList<>();
		this.rand = rand;
	}
```
to
```
	Deck(SecureRandom rand, List deck) {
		this.deck = deck;
		this.rand = rand;
	}
```
Otherwise, methods like drawCard, insertCardAtIndex are not unit testable.
7. As there are about 5 weeks left, I suggest trying to finish Game Setup Phase (including the UI) no later than the end of week 6 to avoid an overload of work at the end, since there are still the cards that each person needs to implement, integration testing, i18n, and user acceptance testing the team needs to do.
