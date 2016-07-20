package com.example.s1;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import com.example.s1.DataBase;



public class GamingMaps extends FragmentActivity implements OnMapReadyCallback {
    // -----測試宣告-----
    private Marker crop = null;
    boolean cropbl=false,isCropbl=false;
    Calendar c=Calendar.getInstance(),c2=Calendar.getInstance();
    double croprage=0.02;
    private TextView tv1, tv2;
    private ImageButton imageButton;
    private int[] picture = new int[]{
            R.drawable.angel, R.drawable.assassin, R.drawable.asura, R.drawable.bandit, R.drawable.bat, R.drawable.behemoth, R.drawable.captain, R.drawable.chimera,
            R.drawable.cockatrice, R.drawable.darklord, R.drawable.death, R.drawable.demon, R.drawable.dragon, R.drawable.earthspirit, R.drawable.evilgod, R.drawable.evilking,
            R.drawable.fairy, R.drawable.fanatic, R.drawable.firespirit, R.drawable.gargoyle, R.drawable.garuda, R.drawable.gayzer, R.drawable.general, R.drawable.ghost,
            R.drawable.god, R.drawable.goddess, R.drawable.hornet, R.drawable.icelady, R.drawable.ifrit, R.drawable.imp, R.drawable.jellyfish, R.drawable.lamia, R.drawable.mage,
            R.drawable.mimic, R.drawable.ogre, R.drawable.orc, R.drawable.plant, R.drawable.priest, R.drawable.puppet, R.drawable.rat, R.drawable.rogue, R.drawable.sahagin,
            R.drawable.scorpion, R.drawable.skeleton, R.drawable.slime, R.drawable.snake, R.drawable.soldier, R.drawable.spider, R.drawable.succubus, R.drawable.swordman,
            R.drawable.vampire, R.drawable.waterspirit, R.drawable.werewolf, R.drawable.willowisp, R.drawable.windspirit, R.drawable.zombie
    };
    private picture pic = new picture();
    private Random random = new Random();
    private int randompic = (int) (random.nextInt(pic.pic));
    double lat,lng;
    LocationManager mgr;
    LocationListener thos;
    // -----類別變數-----
    private GoogleMap mMap;
    private LatLng NIU;
    private PolygonOptions polygon;
    private PolylineOptions polyline;
    private CircleOptions circle;
    private MarkerOptions NIUOp;
    private Marker mNIU;
    //other set
    int[] monster_amount, gameStage;
    int DEMON, SLAME;
    HashMap<Integer, Marker>
            demonHashMap,
            slameHashMap,
            gameStageMarker;

    private Button btn_battle, btn_test_new_features, btn_clear_monster, btn_story_dialog;

    // -----部分內容-----
    private LatLng TW_NUMBER_ONE = new LatLng(-34, 151);

    // -----生命週期-----
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        findview();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mMap = mapFragment.getMap();//come on (GoogleMap googleMap)
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);

        mgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        thos = (LocationListener) new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String str = "定位提供者:" + location.getProvider();
                str += String.format("\n緯度:%.5f\n經度:%.5f\n高度:%.2f公尺",
                        location.getLatitude(),
                        location.getLongitude(),
                        location.getAltitude());
                str += "Create by Meng-Ting Tsai";
                tv1.setText(str);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        String provider = mgr.getBestProvider(new Criteria(), true);
        if (provider != null) {
            tv2.setText("取得資料中v0.0716.2");
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mgr.requestLocationUpdates(provider, 5000, 5, thos);
        } else tv2.setText("請開啟GPS");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mgr.removeUpdates(thos);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        createsetting();
        setlistence();
        //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude()),16));
        //draggable
        mMap.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {
                NIUOp.position(marker.getPosition());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NIUOp.getPosition(), 16));
                marker.setSnippet(marker.getId());
            }

            @Override
            public void onMarkerDrag(Marker marker) {

            }

            @Override
            public void onMarkerDragEnd(Marker marker) {
                NIUOp.position(marker.getPosition());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NIUOp.getPosition(), 16));
            }
        });
        //MarkerClick
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                                          @Override
                                          public boolean onMarkerClick(final Marker marker) {
                                              try {
                                                  //region Description
//                                                  if (marker.getSnippet().substring(0, 2).equals("11")) {
//                                                      GamingMaps.this.finish();
//                                                  }
//                                                  if (marker.getSnippet().substring(0, 2).equals("13")) {
//                                                      Intent intent = new Intent();
//                                                      intent.setClass(GamingMaps.this, BattleActivity.class);
//                                                      Bundle bundle = new Bundle();
//                                                      bundle.putString("number", marker.getSnippet());
//                                                      intent.putExtras(bundle);
//                                                      //startActivityForResult(intent,1);
//                                                      startActivity(intent);
//                                                      marker.remove();
//                                                  }
//                                                  if (marker.getId().equals(demonHashMap.get(marker))) {
//                                                  }
//                                                  getApplicationInfo();
                                                  //endregion
                                                  final Intent intent = new Intent();
                                                  intent.setClass(GamingMaps.this, BattleActivity.class);
                                                  //intent.setClass(GamingMaps.this, testactivity.class);
                                                  Bundle bundle = new Bundle();
                                                  bundle.putString("number", marker.getSnippet());
                                                  intent.putExtras(bundle);
                                                  String[] stats = marker.getSnippet().split(":");//切割字串
                                                  String str = "來自難度:" + marker.getTitle();
                                                  str += String.format("\n攻擊力:%s\n未定欄項:%s\n血量:HP%s\n邪惡度:%s個旺旺集團",
                                                          stats[0],
                                                          stats[1],
                                                          stats[2],
                                                          stats[3]);
                                                  AlertDialog.Builder builder = new AlertDialog.Builder(GamingMaps.this);
                                                  builder.setMessage(str)
                                                          .setCancelable(false)
                                                          .setNeutralButton("挑戰", new DialogInterface.OnClickListener() {
                                                              public void onClick(DialogInterface dialog, int id) {
                                                                  startActivity(intent);
                                                                  marker.remove();
                                                              }
                                                          })
                                                          .setNegativeButton("放棄", new DialogInterface.OnClickListener() {
                                                              public void onClick(DialogInterface dialog, int id) {
                                                                  dialog.cancel();
                                                              }
                                                          });
                                                  AlertDialog alert = builder.create();
                                                  alert.show();
                                              } catch (Exception e) {
                                                  e.printStackTrace();
                                              }
                                              return false;

                                          }
                                      }
        );
    }

    // -----類別方法-----
    private void findview() {
        //imagebutton define
        imageButton = (ImageButton) findViewById(R.id.imageButton);
        //textview define
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView4);
        //button define
        btn_battle = (Button) findViewById(R.id.battle);
        btn_test_new_features = (Button) findViewById(R.id.test_new_features);
        btn_clear_monster = (Button) findViewById(R.id.clear_monster);
        btn_story_dialog = (Button) findViewById(R.id.story_dialog);
    }

    private void createsetting() {

        //Add a marker in Sydney and move the camera
        NIU = new LatLng(24, 121);
        polygon = new PolygonOptions().add(new LatLng(24, 121), NIU, new LatLng(24, 121));
        polyline = new PolylineOptions();
        circle = new CircleOptions().center(NIU).radius(6000);
        //other set
        monster_amount = new int[]{0, 0};
        DEMON = 0;
        SLAME = 1;
        gameStage = new int[]{0, 1};

        demonHashMap = new HashMap<Integer, Marker>();
        slameHashMap = new HashMap<Integer, Marker>();
        gameStageMarker = new HashMap<Integer, Marker>();
        //set start
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
            }
        }
        NIUOp = new MarkerOptions().position(NIU).title("Marker in NIU").draggable(true).snippet("22");
        mNIU = mMap.addMarker(NIUOp);
       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NIU, 16));
        mMap.setMyLocationEnabled(true);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(NIU));

    }

    private void setlistence() {
        //button listener
        btn_battle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                try {//region Description
                    //region setting
                    String str1 = "定位提供者:" + mMap.getMyLocation().getProvider();
                    str1 += String.format("\n緯度:%.5f\n經度:%.5f\n高度:%.2f公尺",
                            mMap.getMyLocation().getLatitude(),
                            mMap.getMyLocation().getLongitude(),
                            mMap.getMyLocation().getAltitude());
                    str1 += "Create by Meng-Ting Tsai";
                    tv1.setText(str1);
                    /*Date d = new Date();
                    final CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm:ss", d.getTime());
                    final String str = "( " + String.valueOf(s) + " )";*/
                    //endregion
                    AlertDialog.Builder builder = new AlertDialog.Builder(GamingMaps.this);
                    builder.setMessage("你有看清真相的覺悟嗎?\n選擇的難度越高，出現的怪物越多喔")
                            .setNeutralButton("Hard(3隻)", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    for (int i = 0; i < 3; i++) {
                                        LatLng Rand = new LatLng(mMap.getMyLocation().getLatitude() - 0.01 * Math.random() + 0.005, mMap.getMyLocation().getLongitude() - 0.01 * Math.random() + 0.005);
                                        randompic = random.nextInt(picture.length);
                                        mMap.addMarker(
                                                new MarkerOptions().position(Rand).icon(BitmapDescriptorFactory.fromResource(DataBase.picture[randompic])).title("Hard").snippet(DataBase.monster[randompic]).draggable(true));
                                    }
                                }
                            })
                            .setNegativeButton("Normal(2隻)", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    for (int i = 0; i < 2; i++) {
                                        LatLng Rand = new LatLng(mMap.getMyLocation().getLatitude() - 0.01 * Math.random() + 0.005, mMap.getMyLocation().getLongitude() - 0.01 * Math.random() + 0.005);
                                        randompic = random.nextInt(picture.length);
                                        mMap.addMarker(
                                                new MarkerOptions().position(Rand).icon(BitmapDescriptorFactory.fromResource(DataBase.picture[randompic])).title("Normal").snippet(DataBase.monster[randompic]).draggable(true));
                                    }
                                }
                            })
                            .setPositiveButton("Eazy(1隻)", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    for (int i = 0; i < 1; i++) {
                                        LatLng Rand = new LatLng(mMap.getMyLocation().getLatitude() - 0.01 * Math.random() + 0.005, mMap.getMyLocation().getLongitude() - 0.01 * Math.random() + 0.005);
                                        randompic = random.nextInt(picture.length);
                                        mMap.addMarker(
                                                new MarkerOptions().position(Rand).icon(BitmapDescriptorFactory.fromResource(DataBase.picture[randompic])).title("Eazy").snippet(DataBase.monster[randompic]).draggable(true));
                                    }
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                    //endregion
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        btn_test_new_features.setOnClickListener(new Button.OnClickListener() {
            Marker n;

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //region Description
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                switch (gameStage[1]) {
                    case 1:
                        n = mMap.addMarker(new MarkerOptions().position(NIU).title("村莊1").icon(BitmapDescriptorFactory.fromResource(R.drawable.demon)).visible(true));
                        gameStageMarker.put(gameStage[0], n);
                        n.showInfoWindow();
                        gameStage[0] = gameStage[0] + 1;
                        gameStage[1] = 2;
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(NIU));
                        break;
                    case 2:
                        n = mMap.addMarker(new MarkerOptions().position(new LatLng(24.0007, 121.00007)).title("村莊2").icon(BitmapDescriptorFactory.fromResource(R.drawable.demon)));
                        gameStageMarker.put(gameStage[0], n);
                        n.showInfoWindow();
                        gameStage[0] = gameStage[0] + 1;
                        polyline.add(NIU, new LatLng(24.00007, 121.00007));
                        mMap.addPolyline(polyline);
                        gameStage[1] = 3;
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.00006, 121.00006), 16));
                        break;
                    case 3:
                        n = mMap.addMarker(new MarkerOptions().position(new LatLng(24.000742, 121.000749)).title("村莊3").icon(BitmapDescriptorFactory.fromResource(R.drawable.demon)));
                        gameStageMarker.put(gameStage[0], n);
                        n.showInfoWindow();
                        gameStage[0] = gameStage[0] + 1;
                        polyline.add(NIU, new LatLng(24.0007503, 121.0007599), new LatLng(24.000742, 121.000749));
                        mMap.addPolyline(polyline);
                        gameStage[1] = 4;
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.000742, 121.000749), 16));
                        break;
                    case 4:
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NIU, 16));
                        for (int i = 0; i < 1; i++) {
                            LatLng NIUD = new LatLng(NIUOp.getPosition().latitude - 0.01 * Math.random() + 0.015, NIUOp.getPosition().longitude - 0.01 * Math.random() + 0.005);
                            Marker n = mMap.addMarker(new MarkerOptions().position(NIUD).title("I'm copy from NIU").icon(BitmapDescriptorFactory.fromResource(R.drawable.smallslime)).snippet("11:" + i + ":20" + ":20").draggable(true));//snippet(monstor number:i number:monster hp:monster Attack Power)
                            slameHashMap.put(monster_amount[SLAME], n);
                            monster_amount[SLAME] = monster_amount[SLAME] + 1;
                        }
                        for (int i = 0; i < 5; i++) {
                            LatLng NIUD = new LatLng(NIUOp.getPosition().latitude - 0.01 * Math.random() + 0.005, NIUOp.getPosition().longitude - 0.01 * Math.random() + 0.005);
                            Marker m = mMap.addMarker(new MarkerOptions().position(NIUD).title("I'm copy from NIU").icon(BitmapDescriptorFactory.fromResource(R.drawable.smalldemon)).snippet("13:" + i + ":100" + ":20").draggable(true));//snippet(monstor number:i number:monster hp:monster Attack Power)
                            demonHashMap.put(monster_amount[DEMON], m);
                            monster_amount[DEMON] = monster_amount[DEMON] + 1;
                        }
                    case 5:
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(NIU, 16));
                        for (int i = 0; i < 1; i++) {
                            LatLng NIUD = new LatLng(NIUOp.getPosition().latitude - 0.01 * Math.random() + 0.015, NIUOp.getPosition().longitude - 0.01 * Math.random() + 0.005);
                            Marker n = mMap.addMarker(new MarkerOptions().position(NIUD).title("I'm copy from NIU").icon(BitmapDescriptorFactory.fromResource(R.drawable.smallslime)).snippet("11:" + i + ":20" + ":20").draggable(true));//snippet(monstor number:i number:monster hp:monster Attack Power)
                            slameHashMap.put(monster_amount[SLAME], n);
                            monster_amount[SLAME] = monster_amount[SLAME] + 1;
                        }
                        for (int i = 0; i < 5; i++) {
                            LatLng NIUD = new LatLng(NIUOp.getPosition().latitude - 0.01 * Math.random() + 0.005, NIUOp.getPosition().longitude - 0.01 * Math.random() + 0.005);
                            Marker m = mMap.addMarker(new MarkerOptions().position(NIUD).title("I'm copy from NIU").icon(BitmapDescriptorFactory.fromResource(R.drawable.smalldemon)).snippet("13:" + i + ":100" + ":20").draggable(true));//snippet(monstor number:i number:monster hp:monster Attack Power)
                            demonHashMap.put(monster_amount[DEMON], m);
                            monster_amount[DEMON] = monster_amount[DEMON] + 1;
                        }
                    default:
                }
                //endregion
            }

        });
        btn_clear_monster.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //region Description
                for (int thread = 0; thread < monster_amount[DEMON]; thread++) {
                    demonHashMap.get(thread).remove();
                    demonHashMap.put(monster_amount[DEMON], mNIU);
                }
                monster_amount[DEMON] = 0;
                //endregion
            }
        });
        btn_story_dialog.setOnClickListener(new Button.OnClickListener() {
            final View item = LayoutInflater.from(GamingMaps.this).inflate(R.layout.dialog_signin, null);

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent CALL_STORY_ALRETDIALOG = new Intent(GamingMaps.this, AlretDialog_StageStory.class);
//                startActivity(CALL_STORY_ALRETDIALOG);
                //String uri = "google.streetview:cbll=42.352299,-71.063979&cbp=1,0,,0,1.0&mz=12";
                String uri = "google.navigation:q=25.0465623,121.4822443";
                Intent streetView = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                streetView.setPackage("com.google.android.apps.maps");
                startActivity(streetView);
            }
        });
        mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
            @Override
            public boolean onMyLocationButtonClick() {
                if(isCropbl==false)
                {
                    tv1.setText(""+(c2.getTimeInMillis()-c.getTimeInMillis()));
                    AlertDialog.Builder builder = new AlertDialog.Builder(GamingMaps.this);
                    builder.setMessage("新增:作物座標 點選自身周圍的點即可播種。(等待100秒收成)")
                            .setNegativeButton("好，馬上來測試。" , new DialogInterface.OnClickListener(){
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    isCropbl=true;
                                }
                            })
                            .setPositiveButton("等等，我先玩其他功能。", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).show();
                }
                return false;
            }
        });
        imageButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                if(isCropbl==true)
                {
                    //region Description
                    double
                            latl=mMap.getMyLocation().getLatitude()-latLng.latitude,
                            lngl=mMap.getMyLocation().getLongitude()-latLng.longitude;
                    if(latl>croprage||lngl>croprage)
                    {tv1.setText("距離太遠");}
                    else
                    {
                        if(cropbl==false)
                        {
                            crop = mMap.addMarker(//玩家位置的種植物
                                    new MarkerOptions().
                                            position(
                                                    new LatLng(
                                                            latLng.latitude,
                                                            latLng.longitude)
                                            ).
                                            icon(BitmapDescriptorFactory.fromResource(R.drawable.crop)).
                                            title("剛被你種下的香菇").snippet("等待的期間 為何不探索一下你的周圍呢?")
                            );
                            tv1.setText("種瓜得瓜，種香菇得香菇:P");
                            cropbl = true;
                            c = Calendar.getInstance();
                        }
                        else if(cropbl==true)
                        {
                            c2 = Calendar.getInstance();
                            if((c2.getTimeInMillis()-c.getTimeInMillis())>100000)
                            {
                                tv1.setText("香菇成長了!!\n你做到了!台北金城武");
                                crop.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.growcrop));
                                crop.setTitle("你可以得到兩個香菇了!!");
                                crop.setSnippet("...好啦 我還沒寫好QQ");
                            }
                            else
                            {
                                tv1.setText("耐心點..你的作物正在成長中");
                            }
                        }
                    }
                    //endregion
                }
            }
        });
    }

    private void oncreate(GoogleMap mMap, int monster, int number) {
        Location location = mMap.getMyLocation();
        String
                s = String.valueOf(location.getLatitude()),
                t = s.substring(0, s.indexOf(".") + 8),
                q = String.valueOf(location.getLatitude()),
                p = q.substring(0, s.indexOf(".") + 8);
        int
                c = Integer.parseInt(t),
                d = Integer.parseInt(p);

        mMap.addMarker(new MarkerOptions().position(new LatLng(c, d)).title("I'm copy from NIU").icon(BitmapDescriptorFactory.fromResource(R.drawable.smalldemon)).snippet(monster + ":" + number + ":100" + ":20").draggable(true));

    }

    private void addSQLData() {

    }


}
