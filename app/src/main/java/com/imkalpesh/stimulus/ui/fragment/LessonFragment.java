package com.imkalpesh.stimulus.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.gms.ads.AdRequest;
import com.imkalpesh.stimulus.R;
import com.imkalpesh.stimulus.baseclasses.BaseAdapter;
import com.imkalpesh.stimulus.baseclasses.BaseFragment;
import com.imkalpesh.stimulus.databinding.ActivityGridBinding;
import com.imkalpesh.stimulus.listeners.generals.CommonActionListener;
import com.imkalpesh.stimulus.models.DashboardChildModel;
import com.imkalpesh.stimulus.models.LessonModel;
import com.imkalpesh.stimulus.ui.activity.LessonListActivity;
import com.imkalpesh.stimulus.utility.AppConstants;

import java.util.ArrayList;

public class LessonFragment extends BaseFragment implements CommonActionListener {
    private ActivityGridBinding binding;
    private ArrayList<DashboardChildModel> lessonArraylist;
    private ArrayList<LessonModel> lessonModelArrayList;

    @Override
    public Activity registerActivity() {
        return getActivity();
    }

    @Override
    public Fragment registerFragment() {
        return this;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareIntertitialAds();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_grid, container, false);
        binding.actionBar.actionBarMainLayout.setVisibility(View.GONE);
        lessonArraylist = new ArrayList<>();
        startWork();
        if (AppConstants.isSaved) {
            binding.adView.setVisibility(View.GONE);
        } else {
            AdRequest adRequest = new AdRequest.Builder().build();
            binding.adView.loadAd(adRequest);
        }

        bindingAdsView(binding.adView);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        binding.rvGrid.setLayoutManager(staggeredGridLayoutManager);
        BaseAdapter dashboardChildAdapter = new BaseAdapter(getActivity(), lessonArraylist, R.layout.dashboard_child_item, this);
        binding.rvGrid.setAdapter(dashboardChildAdapter);
        return binding.getRoot();
    }

    private void startWork() {
        lessonArraylist.add(new DashboardChildModel("Life Goals", R.drawable.bg_ll_l, 1));
        lessonArraylist.add(new DashboardChildModel("Career", R.drawable.bg_ll_c, 1));
        lessonArraylist.add(new DashboardChildModel("Lifestyle", R.drawable.bg_ll_ll, 1));
        lessonArraylist.add(new DashboardChildModel("Valuable", R.drawable.bg_ll_v, 1));
        lessonArraylist.add(new DashboardChildModel("Coming Soon", R.drawable.coming_soon, 1));
    }

    @Override
    public void onViewClick() {

    }

    @Override
    public void onInfoClick(int itemPosition) {

    }

    @Override
    public void onEditClick(int itemPosition) {

    }

    @Override
    public void onDeleteClick(int itemPosition) {

    }

    @Override
    public void onChildClick(Object object) {
        DashboardChildModel childModel = (DashboardChildModel) object;
        Intent intent2 = new Intent(getActivity(), LessonListActivity.class);
        switch (childModel.getChildItemTitle()) {
            case "Life Goals":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Never give up.", "Tenacity is the virtue of sticking it out and never throwing in the towel when things start to get tough. Many people who have regrets are those who quit when success was almost within reach because the way was too hard. Just keep going."));
                lessonModelArrayList.add(new LessonModel("Embrace simplicity.", "Practice the art and virtue in all aspects of your life. When you de-clutter and remove the excesses in your life, you will gain more peace and appreciate life more."));
                lessonModelArrayList.add(new LessonModel("Time is your ally. Use it wisely.", "Our time is short. We could die at any time. Live your life like there is no tomorrow and leave no regrets behind."));
                lessonModelArrayList.add(new LessonModel("Beware of excesses.", "There is the line that goes, “Too much of a good thing is bad.” Focus on quality instead of quantity, be it in about the things you own or the activities you do."));
                lessonModelArrayList.add(new LessonModel("You will not always get what you wish for.", "But you will always be given what you need at the right moment. Sometimes, you don’t even you realize you need it, whether it’s a life lesson, a material thing, a person, or an animal."));
                lessonModelArrayList.add(new LessonModel("You can learn a lot from children.", "Compared to adults, kids have an unbiased and open view of the world. They have this ability to put things in perspective in a way that many adults do not have or have forgotten."));
                lessonModelArrayList.add(new LessonModel("Love with all your might.", "To love and be loved in return makes everything worthwhile. So love your family and your friends. Do not take them for granted."));
                lessonModelArrayList.add(new LessonModel("Love with all your might.", "To love and be loved in return makes everything worthwhile. So love your family and your friends. Do not take them for granted."));
                lessonModelArrayList.add(new LessonModel("Failure is the greatest source of wisdom.", "When you fail at something, take a look at what you’ve been doing. Adjust or change your approach until you achieve your objectives."));
                lessonModelArrayList.add(new LessonModel("There is power in silence.", "In conversations, you gain so much by listening more and allowing others to speak their turn."));
                lessonModelArrayList.add(new LessonModel("Express your feelings", "Tell the people you care about how much you love them. If things upset you, learn to assert yourself without being aggressive. There is no positive benefit from bottling up your emotions. On the contrary, it can create health problems in the long run."));
                lessonModelArrayList.add(new LessonModel("Travel is the best education you’ll get.", "You will learn so much – about a culture, a place, and yourself – when you travel. Do it as often as you can. It is the best education you’ll have in the University of Life."));
                lessonModelArrayList.add(new LessonModel("Things become clutter.", "Things you have accumulated today might be cause for annoyance later. Learn how to make mindful purchases and avoid impulse buying."));
                lessonModelArrayList.add(new LessonModel("All of these are temporary.", "Whatever challenges you’re experiencing now are all temporary. They, too, will pass. The same goes for the good times. Prepare your mind to accept change."));
                lessonModelArrayList.add(new LessonModel("Your words have power.", "Always strive to mean what you say. Use your words sparingly and wisely."));
                lessonModelArrayList.add(new LessonModel("Life will hit you hard.", "But the important thing is regardless of how hard you’re hit you keep moving on. Having the ability to endure no matter what challenges are thrown your way is the key to winning in life."));
                break;

            case "Career":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Success starts from inside out.", "Many driven and successful people concentrate on every aspect of their work life but neglect their health. Being busy, productive, and successful is great, but you need to stay healthy to keep up the pace. Make sure you're getting enough nutrition, exercise, and rest."));
                lessonModelArrayList.add(new LessonModel("In relationships, the little things are the big things.", "The most successful people tend to have the broadest and most diverse social networks. And they aren't just superficial acquaintances, but a broad network of genuine connections. Developing relationships is a very important part of doing business. The more time and effort you put into the little things, the more important they become."));
                lessonModelArrayList.add(new LessonModel("Your social life belongs offline.", "If you spend hours on Facebook, Twitter, or Instagram, checking emails and messages, you're missing out on more than you're gaining. Connecting to the world means spending time with people in real life--speaking to them, interacting with them, and connecting with them."));
                lessonModelArrayList.add(new LessonModel("Success is incremental.", "Overnight success is seriously rare. Most people take years, even decades, to build a great career. The secret is to work hard every single day, and remember that even small wins can boost your life tremendously. Over time, you'll find yourself reaching bigger and bigger goals."));
                lessonModelArrayList.add(new LessonModel("Together is always better.", "Many people would rather go it alone, but success is a team sport. The idea that you can succeed on your own is a myth. Every big idea deserves a team to make it happen."));
                lessonModelArrayList.add(new LessonModel("Failure is never fatal.", "Like it or not, messing up is an essential part of the success process. Failure is never the end--it's the opportunity to do better next time. Those who fail and are brave enough to try again eventually win out."));
                lessonModelArrayList.add(new LessonModel("Don't let silly things rob your happiness.", "So many of us allow the most ridiculous little things to rob us of our happiness. But we can always choose to be happy. Life is a choice; happiness is a choice. No matter what your situation, don't let anything separate you from a sense of joy."));
                lessonModelArrayList.add(new LessonModel("Everyone is winging it", "Some of us have more education, experience, and training, but at the heart of it, we’re all playing the same guessing game."));
                break;

            case "Lifestyle":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Sitting in dining halls for hours is dangerous.", "We all know socializing is the best part of college, and I have found myself talking to my friends over dinner for hours at a time in the dining halls. Socializing isn't a bad thing, but surrounding yourself with endless food is. I found myself grazing the dessert table several times in one night. Get up and talk in the dorms. Go for a walk. Just get yourself away from the food"));
                lessonModelArrayList.add(new LessonModel("It's ok to snack.", "In theory cutting out snacks saves calories; however, in reality, it cause you to binge at meal times. When I go to the dining halls starving, I eat so fast that I don't even know when I'm full. Snack but snack smart by getting these five snacks every college student needs in their dorm room."));
                lessonModelArrayList.add(new LessonModel("Buy single serving snacks.", "Portion sizes are essential to a good diet, but when you have a family size bag of potatoe chips it's hard to keep track of how much you are really eating. I even try to convince myself that I've only had one serving when really I had three or four. Buying small, prepackaged foods will help you stay on track."));
                lessonModelArrayList.add(new LessonModel("The late night trip to Wawa isn't worth it.", "Trust me I know that everything tastes better when your drunk, but just go to bed. You will thank me in the morning. "));
                lessonModelArrayList.add(new LessonModel("Give A Little of Yourself For Free.", "There's nothing that gives you as big a warm fuzzy feeling as helping out others in need or less fortunate than yourself. Volunteering, even just for a couple of hours a week or a month, really puts life into perspective, which is good for your soul. Many people rely on the kindness of strangers to make their world a better place to live in."));
                lessonModelArrayList.add(new LessonModel("Stop Burning the Candle at Both Ends", "Trying to fit as much into your day as you can leaves little time for sleep.But people who sleep more are generally healthier. Their memories are improved, their problem-solving abilities remain sharp, and they act in a more reasoned way. Research also indicates they are less likely to put on weight or develop type 2 diabetes, and most likely to live to 100."));
                break;

            case "Valuable":
                lessonModelArrayList = new ArrayList<>();
                lessonModelArrayList.add(new LessonModel("Serve over everything.", "If you want to grow your business, find ways you can serve more people while also looking for more efficient ways to serve your existing customers. When I started to direct my focus away from all that I could potentially gain to serving more people, my business, impact and life changed drastically. "));
                lessonModelArrayList.add(new LessonModel("The power of a strong, clear and massive vision is incredible.", "If you need a breakthrough, you must first start to fully believe that the future is going to be bigger than the past. I had a lot of short-term disappointments in 2015, and the only way I was able to keep moving forward despite the setbacks was because of the vision that I created."));
                lessonModelArrayList.add(new LessonModel("Persistence always pays off.", "It may not always be when we want it to happen, but if we stay persistent day in and day out, we eventually will get to where we want to go. Staying persistent for most people is extremely difficult, because most people seek instant gratification and praise."));
                lessonModelArrayList.add(new LessonModel("Never settle for average.", "Throughout all of my travels in 2015, the one constant negative trait that continued to pop up was all of the men and women who have reached a settlement with mediocrity. They have stopped expanding, growing, and working to become world-class at what they do. You may never rise to be the absolute best at what it is you do for a living, but never settle for average or just doing enough to get by. I have seen plenty of people as both an athlete and in the business arena with very minimal talent go on to become a peak performer, simply because they were hungry enough to not be average."));
                lessonModelArrayList.add(new LessonModel("Relationships rule.", "At the end of the day, what matters most in life are the people in our lives. Put them first every single day. Before work. Before the computer. Before your hobbies. Treat them like they are your everything. Because they are."));
                lessonModelArrayList.add(new LessonModel("Things gather dust.", "Time and money spent accumulating material things will one day irritate you. You have to clean, maintain, store, and move stuff. The less stuff you have in your life, the freer you are. Purchase mindfully. Simplify. Declutter your life."));
                lessonModelArrayList.add(new LessonModel("Love yourself more of the time.", "So many of us, including myself, look to help and love others way before we even start to pay attention to our own well-being and happiness. This doesn’t mean to boast about yourself -- but to pay more attention to yourself. When we love ourselves more of the time, we have that much more to give to the world, our business and other people."));
                lessonModelArrayList.add(new LessonModel("Failure is good.", "We try so hard to avoid failure, but failure is the real evidence that we've had the courage to try. If you avoid failure, you avoid taking action. Expect and accept that failure is part of the experience. Learn from it, grow from it, and move on."));
                break;

            case "Coming Soon":
                AppConstants.UDER_DEVELOPMENT = true;
                break;
        }
        if (!AppConstants.UDER_DEVELOPMENT) {
            intent2.putParcelableArrayListExtra(AppConstants.LESSON_ARRAYLIST_NAME, lessonModelArrayList);
            intent2.putExtra(AppConstants.LESSON_NAME, childModel.getChildItemTitle());
            startActivity(intent2);
            lessonModelArrayList.clear();
        } else {
            Toast toast = Toast.makeText(getActivity(),
                    "Under Development", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            AppConstants.UDER_DEVELOPMENT=false;
        }
    }

    @Override
    public void onParentClick(Object object) {

    }
}