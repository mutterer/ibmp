#pragma once

#include <map>
#include <set>
using namespace std;
#include "DeviceBase.h"

// Error codes
#define ERR_HUB_PATH (900)

#define ERR_XY_INVALID (1000)
#define ERR_XY_TIMEOUT (1001)
#define ERR_XY_MOVE (1002)

#define ERR_Z_INVALID (1100)
#define ERR_Z_TIMEOUT (1101)
#define ERR_Z_MOVE (1102)

#define ERR_OBJECTIVE_TIMEOUT (1200)
#define ERR_OBJECTIVE_NOTFOUND (1201)

class EventReceiver
{
public:
	virtual void EventHandler(int eventId, int data) = 0;
};

// Hub
class Hub : public HubBase<Hub>
{
public:
	Hub();

	~Hub();

	virtual bool Busy();

	virtual int Initialize();

	virtual int Shutdown();

	virtual void GetName(char* name) const;

	int DetectInstalledDevices();

	void MicroscopeEventHandler(int eventId, int data);

	void SetOn();

	void SetOff();

	bool Wait();

	void AddEventReceiver(EventReceiver* er);

	void RemoveEventReceiver(EventReceiver* er);
private:
	int _init;
	bool _busy;
	HANDLE _event;
	int _timeout;
	set<EventReceiver*> _ers;
};

// Stage
class XYStage : public CXYStageBase<XYStage>, public EventReceiver
{
public:
	XYStage();

	~XYStage();

	virtual bool Busy();

	virtual int Initialize();

	virtual int Shutdown();

	virtual void GetName(char* name) const;

	virtual int GetLimitsUm(double& xMin, double& xMax, double& yMin, double& yMax);

	virtual int SetPositionSteps(long x, long y);

	virtual int GetPositionSteps(long& x, long& y);

	virtual int Home();

	virtual int Stop();

	virtual int SetOrigin();

	virtual int GetStepLimits(long& xMin, long& xMax, long& yMin, long& yMax);

	virtual double GetStepSizeXUm();

	virtual double GetStepSizeYUm();

	virtual int IsXYStageSequenceable(bool& isSequenceable) const;

	void EventHandler(int eventId, int data);
private:
	int _init;
	bool _busy;
};

// Z
class ZStage : public CStageBase<ZStage>, public EventReceiver
{
public:
	ZStage();

	~ZStage();

	virtual bool Busy();

	virtual int Initialize();

	virtual int Shutdown();

	virtual void GetName(char* name) const;

	virtual int SetPositionUm(double pos);

	virtual int GetPositionUm(double& pos);

	virtual int SetPositionSteps(long steps);

	virtual int GetPositionSteps(long& steps);

	virtual int SetOrigin();

	virtual int GetLimits(double& lower, double& upper);

	virtual int IsStageSequenceable(bool& isSequenceable) const;

	virtual bool IsContinuousFocusDrive() const;

	void EventHandler(int eventId, int data);
private:
	int _init;
	bool _busy;
};

// Objectives
class Objectives : public CStateDeviceBase<Objectives>, public EventReceiver
{
public:
	Objectives();

	~Objectives();

	virtual bool Busy();

	virtual int Initialize();

	virtual int Shutdown();

	virtual void GetName(char* name) const;

	virtual unsigned long GetNumberOfPositions() const;

	int OnState(MM::PropertyBase* pProp, MM::ActionType eAct);

	void EventHandler(int eventId, int data);
private:
	int _init;
	bool _busy;
	map<int, double> _mag;
	long _curpos;
};

// Illumination
class Illumination : public CGenericBase<Illumination>, public EventReceiver
{
public:
	Illumination();

	~Illumination();

	virtual bool Busy();

	virtual int Initialize();

	virtual int Shutdown();

	virtual void GetName(char* name) const;

	int OnIntensity(MM::PropertyBase* pProp, MM::ActionType eAct);

	void EventHandler(int eventId, int data);
private:
	int _init;
	bool _busy;
};
