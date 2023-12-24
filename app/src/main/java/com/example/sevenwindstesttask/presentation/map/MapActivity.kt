package com.example.sevenwindstesttask.presentation.map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sevenwindstesttask.R
import com.example.sevenwindstesttask.data.responses.coffeeShops.CoffeeShop
import com.example.sevenwindstesttask.databinding.ActivityMapBinding
import com.example.sevenwindstesttask.helpers.JsonConverter
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.layers.GeoObjectTapListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.GeoObjectSelectionMetadata
import com.yandex.runtime.image.ImageProvider
import dagger.android.AndroidInjection
import java.math.BigDecimal

class MapActivity : AppCompatActivity() {

    /**
     * Считаю что тут нужно оставить хороший комментарий почему не смогу сделать так как задумывалось заданием
     * Задача: Видимо, нужно при нажатии на геолокацию кофейни перемещаться на экран меню
     * точки должны иметь название кофейни и стаканчик кофе в кружочке
     * Причина почему не реализовано: не знаю как в платной верии yandex mapkit, но в бесплаьной версии
     * можно тыкать только на POI (документация ya map kit) point of interests - точка интереса
     * Так же, невозможно выставить точку чем-то другим кроме картинок (ни каким-то view, ни векторным рисунком),
     * поэтому принял решение отметить стаканчиком кофе, ну чтобы показать что умею работать с картами реализую
     * взаимодействие с POI
     */

    private lateinit var binding: ActivityMapBinding

    private var tapListener: GeoObjectTapListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        MapKitFactory.initialize(this)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tapListener = createTapListener()

        val list = JsonConverter<List<CoffeeShop>>().jsonCoffeeShopsToValue(intent.getStringExtra(MapActivityContract.KEY))

        list?.let {coffeeShops ->
            var midLatitude = 0.0
            var midLongitude = 0.0
            repeat(coffeeShops.size){position ->
                midLongitude += coffeeShops[position].point.longitude.toDouble()
                midLatitude += coffeeShops[position].point.latitude.toDouble()
                setPoint(
                    latitude = coffeeShops[position].point.latitude,
                    longitude = coffeeShops[position].point.longitude
                )
            }
            moveMap(midLongitude/coffeeShops.size, midLatitude/coffeeShops.size)
        }
    }

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.map.onStart()
    }

    override fun onResume() {
        super.onResume()

        tapListener?.let {
            binding.map.mapWindow.map.addTapListener(it)
        }

        //Завершение контракта с активити
        binding.toolbarMap.setNavigationOnClickListener {
            finish()
        }
    }

    /**
     * Перемещение в среднюю точку между всеми кофейнями
     */
    fun moveMap(latitude: Double, longitude: Double){
        val placePoint = Point(latitude,longitude)
        binding.map.mapWindow.map.move(
            CameraPosition(
                placePoint,
                10.0f,
                150.0f,
                30.0f
            ),
            Animation(Animation.Type.SMOOTH, 3f)
        ){}
    }

    /**
     * Установка точки доставки по долготе и широте
     */
    private fun setPoint(latitude:BigDecimal,longitude:BigDecimal){
        binding.map.mapWindow.map.resetMapStyles()
        val placePoint = Point(latitude.toDouble(), longitude.toDouble())
        val placeMarker = ImageProvider.fromResource(this, R.drawable.ic_coffee)
        binding.map.mapWindow.map.mapObjects.addPlacemark().apply {
            geometry = placePoint
            setIcon(placeMarker)
            direction
        }
    }

    /**
     * Самое важное тут это удаление слушателя нажатий иначе ничего не тапается при включении активити во второй раз
     */
    override fun onStop() {
        MapKitFactory.getInstance().onStop()
        binding.map.onStop()
        tapListener?.let {
            binding.map.mapWindow.map.removeTapListener(it)
        }
        super.onStop()
    }


    /**
     * создание слушателя нажатий
     */
    private fun createTapListener() =
        GeoObjectTapListener {
            val selectionMetadata: GeoObjectSelectionMetadata = it
                .geoObject
                .metadataContainer
                .getItem(GeoObjectSelectionMetadata::class.java)
            binding.map.mapWindow.map.selectGeoObject(selectionMetadata)
            true
        }
}