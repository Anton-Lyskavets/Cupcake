/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.cupcake.databinding.FragmentStartBinding
import androidx.navigation.fragment.findNavController
import com.example.cupcake.model.OrderViewModel

/**
 * Это первый экран приложения Cupcake. Пользователь может выбрать, сколько кексов заказать.
 */
class StartFragment : Fragment() {
    /*
    получите ссылку на модель общего представления в качестве переменной класса.
     Используйте by activityViewModels()делегат свойства Kotlin из fragment-ktx библиотеки.
     */
    private val sharedViewModel: OrderViewModel by activityViewModels()

    // Экземпляр объекта привязки, соответствующий макету fragment_start.xml
    // Это свойство не равно нулю между обратными вызовами жизненного цикла onCreateView()
    // и onDestroyView(),
    // когда иерархия представлений привязана к фрагменту.
    private var binding: FragmentStartBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentStartBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.startFragment = this
    }

    /**
     * Начните заказ с желаемого количества кексов и перейдите к следующему экрану.
     */
    fun orderCupcake(quantity: Int) {
        /*
        В начале orderCupcake()метода вызовите setQuantity()метод в модели общего представления,
        чтобы обновить количество, прежде чем перейти к фрагменту аромата.
         */
        sharedViewModel.setQuantity(quantity)
        if (sharedViewModel.hasNoFlavorSet()) {
            sharedViewModel.setFlavor(getString(R.string.vanilla))
        }
        findNavController().navigate(R.id.action_startFragment_to_flavorFragment)
    }


    /**
     * Этот метод жизненного цикла фрагмента вызывается, когда иерархия представления,
     * связанная с фрагментом,
     * удаляется. В результате удалите объект привязки.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}