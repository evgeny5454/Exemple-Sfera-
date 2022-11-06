package com.evgeny5454.exemplesfera.data.repository

import androidx.lifecycle.LiveData
import com.evgeny5454.exemplesfera.data.db.AppDao
import com.evgeny5454.exemplesfera.data.model.UserTwo
import com.github.javafaker.Faker
import java.util.*
import javax.inject.Inject

class MockPersonRepositoryImpl @Inject constructor(private val appDao: AppDao) {
    private val faker = Faker()
    private val photoUrl = listOf(
        "https://pixelbox.ru/wp-content/uploads/2021/05/ava-vk-animal-91.jpg",
        "https://uprostim.com/wp-content/uploads/2021/03/image086-77.jpg",
        "https://ulibky.ru/wp-content/uploads/2019/10/avatarki_dlya_vatsap_dlya_devushek_42_28061027.jpg",
        "https://coolsen.ru/wp-content/uploads/2021/09/184.jpg",
        "https://coolsen.ru/wp-content/uploads/2021/06/138-8.jpg",
        "https://drasler.ru/wp-content/uploads/2019/10/%D0%A1%D0%BA%D0%B0%D1%87%D0%B0%D1%82%D1%8C-%D0%BA%D1%80%D1%83%D1%82%D1%8B%D0%B5-%D0%B8-%D0%BB%D1%83%D1%87%D1%88%D0%B8%D0%B5-%D1%84%D0%BE%D1%82%D0%BE-%D0%BD%D0%B0-%D0%B0%D0%B2%D0%B0%D1%82%D0%B0%D1%80%D0%BA%D1%83-%D0%B2-%D0%B2%D0%BA-%D0%B4%D0%BB%D1%8F-%D0%BF%D0%B0%D1%86%D0%B0%D0%BD%D0%BE%D0%B2014.jpg",
        "https://avatarko.ru/img/kartinka/1/pozitiv_smailik.jpg",
        "https://placepic.ru/wp-content/uploads/2021/02/kinopoisk_ru_Brad_Pi-41.jpg",
        "https://heaclub.ru/tim/25d3337a15a55c2bf2489f8cfc222409/muzhskaya-avatarka-so-smislom.jpg",
        "https://imagetext2.ru/pics_max/imagetext_ru_27626.jpg"
    )

    fun getAllRecords(): LiveData<List<UserTwo>> {
        return appDao.getAllRecords()
    }

    fun search(searchQuery: String): LiveData<List<UserTwo>> {
        return appDao.search(searchQuery)
    }

    private fun insertRecord(userTwo: UserTwo) {
        appDao.insertRecords(userTwo)
    }

    fun updateUser(userTwo: UserTwo) {
        appDao.updateUser(userTwo)
    }

    fun getPersonList() {
        var counter = 0
        for (i in 1..100) {
            if (counter < 9) {
                insertRecord(
                    UserTwo(
                        id = UUID.randomUUID(),
                        fullName = faker.name().fullName(),
                        isSubscribe = false,
                        subscribeMe = i % 2 == 0,
                        photoUrl = photoUrl[counter]
                    )
                )
                counter++
            } else {
                insertRecord(
                    UserTwo(
                        id = UUID.randomUUID(),
                        fullName = faker.name().fullName(),
                        isSubscribe = false,
                        subscribeMe = i % 2 == 0,
                        photoUrl = photoUrl[counter]
                    )
                )
                counter = 0
            }
        }
    }
}