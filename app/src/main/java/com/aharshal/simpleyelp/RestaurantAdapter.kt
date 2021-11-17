package com.aharshal.simpleyelp
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_main.*


class RestaurantAdapter(
    val context: Context,
    val restaurants: List<YelpRestaurant>
): RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val tvName = itemView.findViewById<TextView>(R.id.tvName);
        private val ratingBar = itemView.findViewById<RatingBar>(R.id.tvName);
        private val tvNumReviews = itemView.findViewById<TextView>(R.id.tvName);
        private val tvAddress = itemView.findViewById<TextView>(R.id.tvName);
        private val tvCategory = itemView.findViewById<TextView>(R.id.tvName);
        private val tvDistance = itemView.findViewById<TextView>(R.id.tvName);
        private val tvPrice = itemView.findViewById<TextView>(R.id.tvName);
        private val imageView = itemView.findViewById<ImageView>(R.id.tvName);

        // private lateinit var tvName:TextView;
        fun bind(restaurant: YelpRestaurant) {

                tvName.text = restaurant.name
                ratingBar.rating = restaurant.rating.toFloat()
                tvNumReviews.text = "${restaurant.numReviews} Reviews"
                tvAddress.text = restaurant.location.address
            tvCategory.text = restaurant.categories[0].title
            tvDistance.text = restaurant.displayDistance()
            tvPrice.text = restaurant.price
            Glide.with(context).load(restaurant.imageUrl).apply(RequestOptions().transforms(
                CenterCrop(), RoundedCorners(20)
            )).into(imageView)

         }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.bind(restaurant)

    }

    override fun getItemCount() =  restaurants.size


}
