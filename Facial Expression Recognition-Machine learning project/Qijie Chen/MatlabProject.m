%%This codes works for face expression expression, and the face database used here is 84 pain expressions
%%which include 7 expressions for 12 women,

%%predefine some variables
clc;
clear all;
maindir='D:\pain expression subset';
subdir= dir(maindir);
gabortrain=[];       %gabor feature training set
grayclasstrain=[];   %training label
gabortest=[];        %gabor feature test set
grayclasstest=[];    %test label

for i=1:length(subdir)
    if(isequal(subdir(i).name, '.')||...
       isequal(subdir(i).name,'..')||...
       ~subdir(i).isdir)
   continue;
    end  
    subdirpath=fullfile(maindir,subdir(i).name,'*.jpg');
    images=dir(subdirpath);
    
    %%gaborfilter creation, I create a 5 scales and 8 orientation 15*15
    %%gabor filter
    scale=5;  %the Gabor filter set scale
    orientation=8;  %the orientation of Gabor filter set
    gaborarray=cell(scale,orientation);
    rowg=15;    %row of each Gabor filter
    colg=15;    %column of each Gabor filter
    fmax=0.2;   %Maximun frenquency
    kfactor=sqrt(2);    %scale factor for adjacent gabor filter
    thetaa=((sqrt(kfactor)+1)*(sqrt(-log(fmax))))/((sqrt(kfactor)-1)*pi);   %variable ¦Ã
    thetab=(sqrt(-log(fmax)))/(pi*tan(pi/2*orientation));   %variable ¦Ç
    for k=1:scale
        f=fmax/((sqrt(2))^(k-1)); %frequency for gabor filter on each scale
        for l=1:orientation
            angle=((orientation-1)/orientation)*pi;
            gaborfilter=zeros(rowg,colg);
            for x=1:rowg
                for y=1:colg
                xtemp=(x-((rowg+1)/2))*cos(angle)+(y-((colg+1)/2))*sin(angle);
                ytemp=-(x-((rowg+1)/2))*sin(angle)+(y-((colg+1)/2))*cos(angle);
                gaborfilter(x,y)=(f^2/(pi*thetaa*thetab))*exp(-((f^2)*(xtemp^2)/(thetaa^2)+(f^2)*(ytemp^2)/(thetab^2)))*exp(1i*2*pi*f*xtemp);
                end
            end
            gaborarray{k,l}=gaborfilter;
        end
    end
    
    %preprocess the original picture for training set
    for j=1:9
        imagepath1=fullfile(maindir,subdir(i).name,images(j).name);
        imgdataori1=imread(imagepath1);
        imgdata1=imresize(imgdataori1,[100,100]);
        A1=size(imgdata1);
        if length(A1)==3
            imggray1=rgb2gray(imgdata1);
        else
            imgray1=imgdata1;
        end
         imggray1=double(imggray1);
         
       % gaborfeature extraction for training data
        gaborfeature=cell(scale,orientation);
        featurevec=[];
        for k=1:scale
            for l=1:orientation
                gaborfeature{k,l}=imfilter(imggray1, gaborarray{k,l});
            end
        end
        for k=1:scale
            for l=1:orientation
                gaborreduction=abs(gaborfeature{k,l});
                gaborreduction=downsample(gaborreduction,4);
                gaborreduction=downsample(gaborreduction.',4);
                gaborreduction=gaborreduction(:);
                gaborreduction=(gaborreduction-mean(gaborreduction))/std(gaborreduction,1); %normalization
                featurevec=[featurevec;gaborreduction];
            end
        end
        
        %getting gabor feature matrix for training set 
        gabortrain=[gabortrain,featurevec];
        switch(i)
            case 3
        grayclasstrain=[grayclasstrain,1];
            case 4
        grayclasstrain=[grayclasstrain,2];
            case 5
        grayclasstrain=[grayclasstrain,3];
            case 6
        grayclasstrain=[grayclasstrain,4];
            case 7
        grayclasstrain=[grayclasstrain,5];
            case 8
        grayclasstrain=[grayclasstrain,6];
            case 9
        grayclasstrain=[grayclasstrain,7];
        end
    end
   
    %preprocess the original picture for testing set
        for j=10:12
        imagepath=fullfile(maindir,subdir(i).name,images(j).name);
        imgdataori=imread(imagepath);
        imgdata=imresize(imgdataori,[100,100]);
        A=size(imgdata);
        if length(A)==3
            imggray=rgb2gray(imgdata);
        else
            imgray=imgdata;
        end
        imggray=double(imggray);
        
      % gaborfeature extraction for testing data
        gaborfeature1=cell(scale,orientation);
        featurevec1=[];
        for k=1:scale
            for l=1:orientation
                gaborfeature1{k,l}=imfilter(imggray, gaborarray{k,l});
            end
        end
        for k=1:scale
            for l=1:orientation
                gaborreduction1=abs(gaborfeature1{k,l});
                gaborreduction1=downsample(gaborreduction1,4);
                gaborreduction1=downsample(gaborreduction1.',4);
                gaborreduction1=gaborreduction1(:);
                gaborreduction1=(gaborreduction1-mean(gaborreduction1))/std(gaborreduction1,1);
                featurevec1=[featurevec1;gaborreduction1];
            end
        end
        
        %getting gabor feature matrix for testing set 
        gabortest=[gabortest,featurevec1];
        switch(i)
            case 3
        grayclasstest=[grayclasstest,1];
            case 4
        grayclasstest=[grayclasstest,2];
            case 5
        grayclasstest=[grayclasstest,3];
            case 6
        grayclasstest=[grayclasstest,4];
            case 7
        grayclasstest=[grayclasstest,5];
            case 8
        grayclasstest=[grayclasstest,6];
            case 9
        grayclasstest=[grayclasstest,7];
        end
    end
end

%using pca to reduce dimension from 25000 to 20 for both training features
%and testing features
gabortrain=gabortrain';
gabortest=gabortest';
trainsum=sum(gabortrain);
utrain=trainsum/63;
bsxfun(@minus,gabortrain,utrain);
gabortraintr=gabortrain';
S=gabortrain*gabortraintr;
[V,D]=eig(S);
eiganvecmat=gabortraintr*V;
eiganvecmatn=eiganvecmat(:,1:20);
featuretraindes=(eiganvecmatn')*gabortraintr;
featuretraindes=featuretraindes';
bsxfun(@minus,gabortest,utrain)
featuretestdes=gabortest*eiganvecmatn;
grayclasstrain=grayclasstrain';
grayclasstest=grayclasstest';

% use SVM as classifier to do classification, here we use the linear kernel
% SVM which implemented under the LIBSVM library, first we should use cross
% validation to identify the best value of paremeter
step=2;
com=0;
com1=0;
while(step>0.1)
    if (step==2)
        init=-8;
        while(init<=8)
        num1=2^(init);
        a2=['-t 0 -v 10 -c ',num2str(num1)];
        model1=svmtrain(grayclasstrain,featuretraindes,a2);  
        if (model1>com)
        com=model1;
        com1=init;
        end
        init=init+step;
        end
        step=step/4;
    else
        init=com1-(step*4);
        final=com1+(step*4);
        while(init<=final)
             num1=2^(init);
             a2=['-t 0 -v 10 -c ',num2str(num1)];
             model1=svmtrain(grayclasstrain,featuretraindes,a2);  
             if (model1>com)
             com=model1;
             com1=init;
             end
             init=init+step;
        end
        step=step/4;
    end
end

%use SVM to predict label
de1=2^(com1);
de2=['-t 0 -c ',num2str(de1)];
modelde=svmtrain(grayclasstrain,featuretraindes,de2);
[PredictLabel,accurac1,decision] = svmpredict(grayclasstest,featuretestdes,modelde);
