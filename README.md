# KotlinChannel
Using kotlin channels to fire one time events from ViewModels

Inspired by this Reddit post: 

https://www.reddit.com/r/androiddev/comments/pdtin2/what_do_you_use_except_singleliveevent_for_one/

I was lead to using Channels as a way of emitting one time events from view models.

Typical use case is when making a network request that will trigger a popup dialog or navigation to a new screen.


These two sources were very useful in understanding the solution:

https://proandroiddev.com/android-singleliveevent-redux-with-kotlin-flow-b755c70bb055

https://medium.com/androiddevelopers/a-safer-way-to-collect-flows-from-android-uis-23080b1f8bda


However, I was left with an issue, "This job has not completed yet", when running tests.

I started digging for a solution (see):

https://stackoverflow.com/questions/61224047/unit-testing-coroutines-runblockingtest-this-job-has-not-completed-yet

https://github.com/Kotlin/kotlinx.coroutines/issues/1204

And eventually have ended up with the code in this repo.

I feel like there are further optimisations to the test code. 

# Testing

Testing Kotlin flows is rather difficult. Not all Flows will terminate on completion, but a Flow that has not terminated will cause the error "This job has not completed yet".

See the following posts for more info.

https://medium.com/google-developer-experts/unit-testing-kotlin-flow-76ea5f4282c5

https://developer.android.com/kotlin/flow/test
