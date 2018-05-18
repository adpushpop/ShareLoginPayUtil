package com;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Toast;
import me.shaohui.bottomdialog.BaseBottomDialog;
import me.shaohui.shareutil.ShareManager;
import me.shaohui.shareutil.ShareUtil;
import me.shaohui.shareutil.share.ShareListener;
import me.shaohui.shareutil.share.SharePlatform;

/**
 * Created by shaohui on 2016/12/10.
 */

public class ShareBottomDialog extends BaseBottomDialog implements View.OnClickListener {

    private ShareListener mShareListener;

    @Override
    public int getLayoutRes() {
        return R.layout.layout_bottom_share;
    }

    @Override
    public void bindView(final View v) {
        v.findViewById(R.id.share_qq).setOnClickListener(this);
        v.findViewById(R.id.share_qzone).setOnClickListener(this);
        v.findViewById(R.id.share_weibo).setOnClickListener(this);
        v.findViewById(R.id.share_wx).setOnClickListener(this);
        v.findViewById(R.id.share_wx_mini).setOnClickListener(this);
        v.findViewById(R.id.share_wx_timeline).setOnClickListener(this);
        v.findViewById(R.id.share_system).setOnClickListener(this);

        mShareListener = new ShareListener() {
            @Override
            public void shareSuccess() {
                Toast.makeText(v.getContext(), "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareFailure(Exception e) {
                Toast.makeText(v.getContext(), "分享失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void shareCancel() {
                Toast.makeText(v.getContext(), "取消分享", Toast.LENGTH_SHORT).show();

            }
        };
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.share_qq:
                ShareUtil.shareImage(getContext(), SharePlatform.QQ,
                        "http://shaohui.me/images/avatar.gif", mShareListener);
                break;
            case R.id.share_qzone:
                ShareUtil.shareMedia(getContext(), SharePlatform.QZONE, "Title", "summary",
                        "http://www.google.com", "http://shaohui.me/images/avatar.gif",
                        mShareListener);
                break;
            case R.id.share_weibo:
                ShareUtil.shareImage(getContext(), SharePlatform.WEIBO,
                        "http://shaohui.me/images/avatar.gif", mShareListener);
                break;
            case R.id.share_wx_timeline:
                ShareUtil.shareText(getContext(), SharePlatform.WX_TIMELINE, "测试分享文字",
                        mShareListener);
                break;
            case R.id.share_wx:
                ShareUtil.shareMedia(getContext(), SharePlatform.WX, "标题", "内容", "http://www.baidu.com", BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher), mShareListener);
                break;
            case R.id.share_wx_mini:
                ShareUtil.shareMedia(getContext(), SharePlatform.WX, "标题", "内容", "http://www.baidu.com", BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_launcher), "gh_41bb43658d5e", "share/card/card", mShareListener);
                break;
            case R.id.share_system:
                ShareUtil.shareText(getContext(), SharePlatform.DEFAULT, "测试分享文字",
                        mShareListener);
                break;
        }
        dismiss();
    }
}
