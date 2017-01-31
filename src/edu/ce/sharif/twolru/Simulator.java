package edu.ce.sharif.twolru;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by mohammad on 1/30/17.
 */
public class Simulator {


    private Long migToDram = 0L;
    private Long dramRead;
    private Long dramWrite;
    private Long pcmRead;
    private Long pcmWrite;
    private Long migToPcm = 0L;

    private final Long DRAM_SIZE;
    private final Long PCM_SIZE;
    private final Long PCM_THRESHOLD;



    public Simulator(Long dramSize, Long pcmSize, Long pcmThreshold)
    {
        this.DRAM_SIZE = dramSize;
        this.PCM_SIZE = pcmSize;
        this.PCM_THRESHOLD = pcmThreshold;
    }

    private List<Page> dram = new ArrayList<Page>(){
        @Override
        public boolean add(Page page) {
            if(this.size()==DRAM_SIZE) {
                migToPcm++;
                pcm.add(0,dram.get(0));
                dram.remove(dram.size()-1);
                dram.add(0,page);
                return true;
            }
            else
                add(0,page);
            return true;
        }
    };

    private List<Page> pcm = new ArrayList<Page>(){
        @Override
        public boolean add(Page page) {
            if(this.size()==PCM_SIZE) {
                pcm.remove(pcm.size()-1);
                pcm.add(0,page);
                return false;
            }
            else
                pcm.add(0,page);
         //  Collections.sort(this);
            return true;
        }
    };


    public void printStatistics(){
        System.out.println("migration to dram: "+migToDram);
        System.out.println("migration to pcm: "+ migToPcm);
    }

    public int add(Page newPage)
    {
        int dramIndex=dram.indexOf(newPage);
        if(dramIndex!=-1) {
            Page p=new Page( dram.get(dramIndex).getAddress());
            p.setAccessNum(dram.get(dramIndex).getAccessNum()+1);

            dram.remove(dramIndex);
            dram.add(p);
            //dram.get(dramIndex).setAccessNum(dram.get(dramIndex).getAccessNum()+1);
        }
        //else
        //    dram.add(newPage);
        else {
            int pcmIndex = pcm.indexOf(newPage);
            if(pcmIndex != -1)
            {
                pcm.get(pcmIndex).setAccessNum(pcm.get(pcmIndex).getAccessNum()+1);
                Page toBeginning=new Page(pcm.get(pcmIndex).getAddress());
                toBeginning.setAccessNum(pcm.get(pcmIndex).getAccessNum());
                pcm.remove(pcmIndex);
                pcm.add(toBeginning);
                if(pcm.get(0).getAccessNum() >= PCM_THRESHOLD) {
                    migToDram++;
                    Page p=new Page(pcm.get(0).getAddress());
                //    p.setAccessNum(pcm.get(pcmIndex).getAccessNum());
                    pcm.remove(0);
                    dram.add(p);
                }

            }
            else{
                if(dram.size()==DRAM_SIZE) {
                    if (pcm.size() == PCM_SIZE) {
                        pcm.remove(pcm.size()-1);
                    }
                    pcm.add(pcm.size(), newPage);
                }
                else
                    dram.add(newPage);
            }
        }
        return 0;
    }

}
