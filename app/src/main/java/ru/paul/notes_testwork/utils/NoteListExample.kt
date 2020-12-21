package ru.paul.notes_testwork.utils

import ru.paul.notes_testwork.model.Note

fun noteListExample(): MutableList<Note> {
    return mutableListOf(
            Note("Погулять", "description1"),
            Note("Поесть", "description2"),
            Note("Поиграть", "description3"),
            Note("Почитать", "description4"),
            Note("Помечтать", "description5"),
            Note("Попрыгать", "description6"),
            Note("Побегать", "description7"),
            Note("Посмеяться", "description8"),
            Note("Погрустить", "description9"),
            Note("Повеселиться", "description10"),
    )
}
