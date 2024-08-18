package saif.saif.moveinsync

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
//import com.javed.moveinsync.R
import com.saif.moveinsync.R
import kotlin.math.abs


class MainActivity : AppCompatActivity(), TrendingAdapter.OnItemClickListener {
    private lateinit var  viewPager2: ViewPager2
    private lateinit var handler : Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var adapter: TrendingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        setUpTransformer()

        val recyclerViewRecommendation = findViewById<RecyclerView>(R.id.recommendationRv)
        val imageList = arrayListOf(
            R.drawable.openheimer,
            R.drawable.barbie,
            R.drawable.pathaan,
            R.drawable.kgf2,
            R.drawable.rrr,
            R.drawable.topgunmarvarik,
            R.drawable.drishyam2,
            R.drawable.avatar2,
            R.drawable.blackpanther,
            R.drawable.vikram // Add other images
        )
        setupHorizontalRecyclerView(imageList, recyclerViewRecommendation)

        val recyclerTop10 = findViewById<RecyclerView>(R.id.top10IndiaRv)
        setupHorizontalRecyclerView(imageList, recyclerTop10)

        val arrayLanguages = arrayListOf("Hindi", "English", "Tamil", "Telugu", "Kannada", "Malayalam")
        val arrayPopularArtist = arrayListOf(
            R.drawable.card1,
            R.drawable.card2,
            R.drawable.card3,
            R.drawable.card4,
            R.drawable.card5,
            R.drawable.card6
        )
        val popularLanguage = findViewById<RecyclerView>(R.id.popularLanguagesRv)
        val colorList = arrayListOf(
            R.color.cardColor1,
            R.color.cardColor2,
            R.color.cardColor3,
            R.color.cardColor4,
            R.color.cardColor5,
            R.color.cardColor6
        )
        setUpHorizontalCardAdapter(arrayLanguages, arrayPopularArtist, colorList, popularLanguage)

        val arrayComicUniverseImages = arrayListOf(
            R.drawable.comic1,
            R.drawable.comic2,
            R.drawable.comic3,
            R.drawable.comic4,
            R.drawable.comic5,
            R.drawable.comic6
        )
        val comicUniverse = arrayListOf("Marvel", "DC", "Dark Horse", "Image", "IDW", "Valiant")
        val comicUniverseRv = findViewById<RecyclerView>(R.id.comicUniverseRv)
        setUpHorizontalCardAdapter(comicUniverse, arrayComicUniverseImages, colorList, comicUniverseRv)


        val freeMovies = findViewById<RecyclerView>(R.id.freeMoviesRv)
        setupHorizontalRecyclerView(imageList, freeMovies)

        val comingSoon = findViewById<RecyclerView>(R.id.comingSoonRv)
        setupHorizontalRecyclerView(imageList, comingSoon)







        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable , 2000)
            }
        })
    }

    override fun onPause() {
        super.onPause()

        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()

        handler.postDelayed(runnable , 2000)
    }

    private val runnable = Runnable {
        viewPager2.currentItem = viewPager2.currentItem + 1
    }

    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.14f
        }

        viewPager2.setPageTransformer(transformer)
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewPager2)
        handler = Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.openheimer)
        imageList.add(R.drawable.barbie)
        imageList.add(R.drawable.pathaan)
        imageList.add(R.drawable.kgf2)
        imageList.add(R.drawable.rrr)
        imageList.add(R.drawable.topgunmarvarik)
        imageList.add(R.drawable.drishyam2)
        imageList.add(R.drawable.avatar2)
        imageList.add(R.drawable.blackpanther)
        imageList.add(R.drawable.vikram)


        adapter = TrendingAdapter(imageList, viewPager2, this)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

    override fun onItemClick(position: Int) {
        val index = position % 10
//        Log.d("Position Movie", "Clicked on $position")
        val movieNames = arrayOf(
            "Oppenheimer", "Barbie", "Pathaan", "KGF 2", "RRR",
            "Top Gun: Maverick", "Drishyam 2", "Avatar 2", "Black Panther", "Vikram"
        )
        val movieName = movieNames[index]
        Toast.makeText(this, "Clicked on $movieName", Toast.LENGTH_SHORT).show()
    }

    private fun setupHorizontalRecyclerView(imageList: ArrayList<Int> = arrayListOf(), recyclerView : RecyclerView){

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager


        val adapter = HorizontalAdapter(imageList, object : HorizontalAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "Clicked item $position", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter = adapter
    }

    private fun setUpHorizontalCardAdapter(textList: ArrayList<String>, imageList: ArrayList<Int> = arrayListOf(), colorList: ArrayList<Int> = arrayListOf(), recyclerView : RecyclerView){

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManager


        val adapter = CardHorizontalAdapter(textList, imageList, colorList, object : CardHorizontalAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                Toast.makeText(this@MainActivity, "Clicked item $position", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView.adapter = adapter
    }

}