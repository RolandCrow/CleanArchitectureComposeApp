package com.example.domain.usecase

import com.example.domain.entity.Post
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetPostUseCase(
    configuration: Configuration,
    private val postRepository: PostRepository
): UseCase<GetPostUseCase.Request, GetPostUseCase.Response>(configuration) {
    override fun process(request: Request): Flow<Response> =
        postRepository.getPost(request.postId)
            .map {
                Response(it)
            }
    data class Request(val postId: Long): UseCase.Request
    data class Response(val post: Post): UseCase.Response

////    “конечно”. Это его ответ, если вы зададите ему вопрос, например: "Как дела?"
// Обычно вопросы заканчиваются вопросительным знаком.
//    "Эй, успокойся!" Это его ответ, если вы НА НЕГО НАКРИЧИТЕ. Обычно для обозначения крика используются ЗАГЛАВНЫЕ БУКВЫ.

//    "Успокойтесь, я знаю, что делаю!" Вот что он говорит, если вы выкрикиваете ему вопрос.

//    "отлично. Будь таким!" Вот как он реагирует на молчание.
//    Для обозначения молчания используется условное обозначение "ничего" или различные комбинации пробельных символов.

//    "Что угодно". Это то, что он отвечает на все остальное.

    fun computeStepCount(start: Int): Int {
        require(start > 0 ) {throw IllegalArgumentException() }
       var steps = 0
        var number = start
        while (number != 1) {
            if (number % 2 == 0) {
                number /= 2
            } else {
                number = 3*number + 1
            }
            steps++
        }
       return steps
    }
}