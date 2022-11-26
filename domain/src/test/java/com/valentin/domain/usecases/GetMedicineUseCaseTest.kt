package com.valentin.domain.usecases

import com.valentin.domain.domain.repository.MedicineRepositoryInterface
import com.valentin.domain.model.Medicine
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class GetMedicineUseCaseTest {

    private val testRepository = mock<MedicineRepositoryInterface>()

    @Test
    fun `should return repository`() = runTest(UnconfinedTestDispatcher()) {
        val testData = Medicine(name = "1", date = "2")

        Mockito.`when`(testRepository.getMedicine()).thenReturn(listOf(testData))

        val useCase = GetMedicineUseCase(repository = testRepository)
        val actual = useCase.execute()
        val expected = listOf(Medicine(name = "1", date = "2"))

        Assertions.assertEquals(expected, actual)
    }

}