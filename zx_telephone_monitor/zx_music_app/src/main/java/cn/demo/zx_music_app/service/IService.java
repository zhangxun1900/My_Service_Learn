package cn.demo.zx_music_app.service;

import java.util.List;

import cn.demo.zx_music_app.domain.MusicInfo;

/**
 * @author Administrator
 * @version $Rev$
 * @time ${DATA} 9:38
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDate $Date$
 * @updateDes ${TODO}
 * Created by xun on 2017/2/14.
 */

public interface IService {
    public void playMusic(List<MusicInfo> list, int position);
}
