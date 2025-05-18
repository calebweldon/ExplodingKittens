# Instructor Feedback

:thought_balloon: Dr. Yiji reviewed the main branch. 
It is very likely that your team has a ton of work in the feature branches. 
So don't worry if the comment below does not seem to match your actual progress. 

## Progress evaluation :scroll:
- :question: Game Setup Phase is complete or near complete.
  - The main branch only has Card and Deck. It needs at least the Player and some sort of GameEngine/Game/GameController too.
- :white_check_mark: Software requirements and/or design are documented. (This one is not required --- do not worry if your team doesn't have it in your repository. It is likely that you have it somewhere else.)
- :question: There is UI code in the main branch. 
- :white_check_mark: Team member contribution brief check: Dr. Yiji looked at the commit history of the main branch and checked whether all the team members are contributing at least a little. 
  - Also, Dr. Yiji uses the "Insights" functionality on GitHub occationally to see whether there is any red flags in terms of individual contribution. To check it yourself, go to the web page of your team's GitHub repository --> Insights tab on the top --> Contributors on the left. Please note that Dr. Yiji doesn't believe in "more # lines of code always means more contribution". But "no or very very few # lines of code" normally indicates something.
- :question: The commit messages are well written. (Again, Dr. Yiji looked at the commit history of the main branch.) 
  - Suggestion: commit messages can be part of the code review as well. 
  - Good examples: Golf0ned and Eiko Reisz's commit messages.

## Comments :speech_balloon:
### General Comments to All Teams
1. Project Week 7 Checklist is available on Canvas. Please check it before the end of Week 7.
2. To clarify: if dependency injection makes SpotBugs reports error about mutability, you can make a "package private" constructor for dependency injection, and use that constructor in the tests.
### Team-specific Comments
1. checkstyle configuration looks pretty good!